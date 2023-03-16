package br.com.ecoffee.dto.usuario;

import java.util.List;

import br.com.ecoffee.model.role.Role;

public class UsuarioDto {

	private Long idUsuario;
	private String nomeUsuario;
	private List<Role> roles;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
