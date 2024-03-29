package br.com.ecoffee.util.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(UserDetails userDetails) {

		Date hoje = new Date();

		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

	    return Jwts.builder()
	            .setIssuer("API do eCoffee")
	            .setSubject(userDetails.getUsername())
	            .setIssuedAt(hoje)
	            .setExpiration(dataExpiracao)
	            .claim("roles", userDetails.getAuthorities())
	            .signWith(SignatureAlgorithm.HS256, secret)
	            .compact();
	}

	public boolean isTokenValido(String token) {

		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
	}

}
