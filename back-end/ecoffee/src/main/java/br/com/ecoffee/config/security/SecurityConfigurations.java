package br.com.ecoffee.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.ecoffee.filters.AutenticacaoTokenFilter;
import br.com.ecoffee.service.cliente.ClienteService;
import br.com.ecoffee.service.security.impl.AutenticacaoService;
import br.com.ecoffee.util.security.TokenService;

@Configuration
public class SecurityConfigurations {

	private ClienteService clienteService;
	private TokenService tokenService;
	
	public SecurityConfigurations(ClienteService clienteService, TokenService tokenService) {
		this.clienteService = clienteService;
		this.tokenService = tokenService;
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(new AutenticacaoService(clienteService))
				.passwordEncoder(new BCryptPasswordEncoder()).and().build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.cors().and().authorizeRequests()
		.antMatchers(HttpMethod.POST, "/cliente/cadastrar").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, clienteService), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
