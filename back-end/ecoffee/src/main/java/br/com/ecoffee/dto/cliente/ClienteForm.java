package br.com.ecoffee.dto.cliente;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.util.security.BcryptUtil;

public class ClienteForm {

	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String nome;
	
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String sobrenome;
	
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String telefone;
	
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String cpf;
	
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String email;
	
	@NotNull
	@NotEmpty
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cliente toCliente() {
		return new Cliente(nome, sobrenome, telefone, cpf, email, BcryptUtil.getHash(senha));
	}

}
