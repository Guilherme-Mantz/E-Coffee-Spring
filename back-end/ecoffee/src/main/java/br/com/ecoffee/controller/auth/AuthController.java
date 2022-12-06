package br.com.ecoffee.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecoffee.dto.cliente.LoginForm;
import br.com.ecoffee.dto.security.TokenDto;
import br.com.ecoffee.service.auth.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/auth")
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm loginForm){
		String token = authService.auth(loginForm);
		
		return ResponseEntity.ok(new TokenDto(token, "Bearer"));

	}
}
