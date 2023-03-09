package br.com.ecoffee.controller.auth;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecoffee.dto.login.FormLogin;
import br.com.ecoffee.dto.security.TokenDto;
import br.com.ecoffee.service.auth.JwtUserDetailsService;
import br.com.ecoffee.util.security.JwtTokenUtil;

@RestController
public class JwtAuthenticationController {

    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService jwtService;
    private AuthenticationManager authenticationManager;

	public JwtAuthenticationController(JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtService,
			AuthenticationManager authenticationManager) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/auth")
    public ResponseEntity<TokenDto> createAuthenticationToken(@RequestBody @Valid FormLogin authenticationRequest) throws Exception {
        
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.gerarToken(userDetails);

        return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    }
	
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } 
        catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } 
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }	
}


