package br.com.ecoffee.repository.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoffee.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByNomeUsuario(String email);

}
