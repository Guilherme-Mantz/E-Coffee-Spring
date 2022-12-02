package br.com.ecoffee.service.login;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.cliente.LoginForm;
import br.com.ecoffee.util.security.TokenService;

@Service
public class LoginService {
	
	private AuthenticationManager authManager;
	private TokenService tokenService;
	
	public LoginService(AuthenticationManager authManager, TokenService tokenService) {
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	@Transactional
	public String login(LoginForm loginForm) {
		UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();
		
		Authentication authenticate = authManager.authenticate(dadosLogin);
		String token = tokenService.gerarToken(authenticate);
		
		return token;
	}
}
