package br.com.ecoffee.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.service.cliente.ClienteService;
import br.com.ecoffee.util.security.TokenService;

public class AutenticacaoTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private ClienteService clienteService;
	
	public AutenticacaoTokenFilter(TokenService tokenService, ClienteService clienteService) {
		this.tokenService = tokenService;
		this.clienteService = clienteService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean tokenValido = tokenService.isTokenValido(token);
		if(tokenValido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		
		Cliente usuario = clienteService.buscarClientePeloId(idUsuario).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);		
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
