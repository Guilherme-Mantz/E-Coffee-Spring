package br.com.ecoffee.dto.usuario;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.ecoffee.model.usuario.Usuario;
import br.com.ecoffee.util.security.BcryptUtil;

public class UsuarioForm {

	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String nomeUsuario;

	@NotNull
	@NotEmpty
	private String senha;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario toUsuario() {
		return new Usuario(nomeUsuario, BcryptUtil.getHash(senha));
	}

}
