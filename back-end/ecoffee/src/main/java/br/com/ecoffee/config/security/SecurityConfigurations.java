package br.com.ecoffee.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.ecoffee.filters.AutenticacaoTokenFilter;
import br.com.ecoffee.service.auth.JwtUserDetailsService;
import br.com.ecoffee.service.cliente.ClienteService;
import br.com.ecoffee.service.usuario.UsuarioService;
import br.com.ecoffee.util.security.JwtTokenUtil;

@Configuration
public class SecurityConfigurations {

	private ClienteService clienteService;
	private UsuarioService usuarioService;
	private JwtTokenUtil jwtTokenUtil;

	public SecurityConfigurations(ClienteService clienteService, UsuarioService usuarioService,
			JwtTokenUtil jwtTokenUtil) {
		this.clienteService = clienteService;
		this.usuarioService = usuarioService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(new JwtUserDetailsService(clienteService, usuarioService))
				.passwordEncoder(passwordEncoder()).and().build();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/cliente/cadastrar").permitAll()
				.antMatchers(HttpMethod.POST, "/usuario/cadastrar").permitAll()
				.antMatchers(HttpMethod.POST, "/auth").permitAll()
				.antMatchers(HttpMethod.GET, "/produto/**").permitAll()
				.antMatchers(HttpMethod.POST, "/admin/**").permitAll()
				.anyRequest().authenticated().and().csrf()
				.disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new AutenticacaoTokenFilter(clienteService, usuarioService, jwtTokenUtil),
						UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
