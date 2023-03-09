package br.com.ecoffee.validation.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecoffee.exception.UniqueException;
import br.com.ecoffee.model.usuario.Usuario;
import br.com.ecoffee.repository.usuario.UsuarioRepository;

@Component
public class UsuarioValidation {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void validarUsuario(Usuario usuario) {

		Optional<Usuario> usuarioEncontrado = usuarioRepository.findByNomeUsuario(usuario.getNomeUsuario());

		if (usuarioEncontrado.isPresent()) {
			throw new UniqueException("Por favor tente outro usuario!", "usuario");
		}
	}

}
