package br.com.ecoffee.util.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.ecoffee.model.cliente.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authenticate) {
		Cliente logado = (Cliente) authenticate.getPrincipal();
		Date hoje = new Date();
		
		Date dataExpiracao = new Date(hoje.getTime() + Long.valueOf(expiration));
		
		return Jwts.builder()
				.setIssuer("API ecoffee")
				.setSubject(logado.getIdCliente().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValido(String token) {

		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}
		catch(Exception e) {
			return false;
		}		
	}

	public Long getIdUsuario(String token) {
		
		try {
			Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
			return Long.valueOf(body.getSubject());
		}
		catch(Exception e) {
			return null;
		}	

	}

}
