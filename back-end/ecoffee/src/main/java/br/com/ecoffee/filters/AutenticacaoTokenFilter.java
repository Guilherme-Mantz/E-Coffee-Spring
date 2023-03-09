package br.com.ecoffee.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.model.usuario.Usuario;
import br.com.ecoffee.service.cliente.ClienteService;
import br.com.ecoffee.service.usuario.UsuarioService;
import br.com.ecoffee.util.security.JwtTokenUtil;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	private ClienteService clienteService;
	private UsuarioService usuarioService;
	private JwtTokenUtil jwtTokenUtil;


	public AutenticacaoTokenFilter(ClienteService clienteService, UsuarioService usuarioService,
			JwtTokenUtil jwtTokenUtil) {
		this.clienteService = clienteService;
		this.usuarioService = usuarioService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		if (jwtTokenUtil.isTokenValido(token)) {
			autenticarUsuario(token);
			autenticarCliente(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		String username = jwtTokenUtil.getUsername(token);

		Optional<Cliente> usuario = clienteService.buscarClientePeloEmail(username);

		if (usuario.isPresent()) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
					usuario.get().getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private void autenticarUsuario(String token) {
		String username = jwtTokenUtil.getUsername(token);

		Optional<Usuario> usuario = usuarioService.buscarPorNomeUsuario(username);

		if (usuario.isPresent()) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
					usuario.get().getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}

		return token.substring(7, token.length());
	}

}
