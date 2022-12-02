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

import br.com.ecoffee.repository.cliente.ClienteRepository;
import br.com.ecoffee.service.security.impl.AutenticacaoService;
import br.com.ecoffee.util.security.AutenticacaoTokenFilter;
import br.com.ecoffee.util.security.TokenService;

@Configuration
public class SecurityConfigurations {

	private AutenticacaoService autenticacaoService;
	private TokenService tokenService;
	private ClienteRepository repository;

	public SecurityConfigurations(AutenticacaoService autenticacaoService, TokenService tokenService, ClienteRepository repository) {
		this.autenticacaoService = autenticacaoService;
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(autenticacaoService)
				.passwordEncoder(new BCryptPasswordEncoder()).and().build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/cliente/*").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, repository), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
