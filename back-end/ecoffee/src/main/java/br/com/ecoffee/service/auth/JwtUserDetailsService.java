package br.com.ecoffee.service.auth;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.model.role.Role;
import br.com.ecoffee.model.usuario.Usuario;
import br.com.ecoffee.service.cliente.ClienteService;
import br.com.ecoffee.service.usuario.UsuarioService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private ClienteService clienteService;
	private UsuarioService usuarioService;

	public JwtUserDetailsService(ClienteService clienteService, UsuarioService usuarioService) {
		this.clienteService = clienteService;
		this.usuarioService = usuarioService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Cliente> clienteOptional = clienteService.buscarClientePeloEmail(username);

		if (clienteOptional.isPresent()) {
			Cliente cliente = clienteOptional.get();
			
			Set<GrantedAuthority> authorities = new HashSet<>();
			for (Role role : cliente.getRoles()) {
			    authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
			}
			
			return new User(cliente.getEmail(), cliente.getSenha(), authorities);
		} 
		else {

			Optional<Usuario> usuarioOptional = usuarioService.buscarPorNomeUsuario(username);
			if (usuarioOptional.isEmpty()) {
				throw new UsernameNotFoundException("User/Email not found.");
			}

			Usuario usuario = usuarioOptional.get();
			
			Set<GrantedAuthority> authorities = new HashSet<>();
			for (Role role : usuario.getRoles()) {
			    authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
			}
			
			return new User(usuario.getNomeUsuario(), usuario.getSenha(), authorities);
		}
	}
}
