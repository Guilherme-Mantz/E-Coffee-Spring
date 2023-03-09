package br.com.ecoffee.service.usuario;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.usuario.UsuarioDto;
import br.com.ecoffee.dto.usuario.UsuarioDtoMapper;
import br.com.ecoffee.dto.usuario.UsuarioForm;
import br.com.ecoffee.model.role.Role;
import br.com.ecoffee.model.usuario.Usuario;
import br.com.ecoffee.repository.role.RoleRepository;
import br.com.ecoffee.repository.usuario.UsuarioRepository;
import br.com.ecoffee.validation.usuario.UsuarioValidation;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	private UsuarioValidation usuarioValidation;
	private RoleRepository roleRepository;
	


	public UsuarioService(UsuarioRepository usuarioRepository, UsuarioValidation usuarioValidation,
			RoleRepository roleRepository) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioValidation = usuarioValidation;
		this.roleRepository = roleRepository;
	}

	@Transactional
	public UsuarioDto cadastrarUsuario (UsuarioForm usuarioForm) {
		
		Usuario usuario = usuarioForm.toUsuario();
		
		usuarioValidation.validarUsuario(usuario);
		
		Role userRole = roleRepository.findByName("EDITOR");
		usuario.setRoles(new HashSet<>(Collections.singletonList(userRole)));
		
		Usuario novoUsuario = usuarioRepository.save(usuario);

		return UsuarioDtoMapper.INSTANCE.toUsuarioDto(novoUsuario);
		
	}

	public Optional<Usuario> buscarPorNomeUsuario(String username) {
		return usuarioRepository.findByNomeUsuario(username);
	}

	public Optional<Usuario> buscarPorIdUsuario(Long idUsuario) {
		return usuarioRepository.findById(idUsuario);
	}

}
