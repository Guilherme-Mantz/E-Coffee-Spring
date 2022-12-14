package br.com.ecoffee.service.auth;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.cliente.LoginForm;
import br.com.ecoffee.util.security.TokenService;

@Service
public class AuthService {
	
	private AuthenticationManager authManager;
	private TokenService tokenService;
	
	public AuthService(AuthenticationManager authManager, TokenService tokenService) {
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	@Transactional
	public String auth(LoginForm loginForm) {
		UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();
		
		Authentication authenticate = authManager.authenticate(dadosLogin);
		String token = tokenService.gerarToken(authenticate);
		
		return token;
	}
}
