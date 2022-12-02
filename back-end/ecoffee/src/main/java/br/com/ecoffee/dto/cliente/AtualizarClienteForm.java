package br.com.ecoffee.dto.cliente;

import br.com.ecoffee.model.cliente.Cliente;

public class AtualizarClienteForm {

	private String nome;
	private String sobrenome;
	private String telefone;
	private String cpf;
	private String email;
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

	public Cliente atualizar(Cliente cliente) {
		
		if(!this.nome.isEmpty()) {
			cliente.setNome(nome);
		}
		
		if(!this.sobrenome.isEmpty()) {
			cliente.setSobrenome(sobrenome);
		}
		
		if(!this.telefone.isEmpty()) {
			cliente.setTelefone(telefone);
		}
		
		if(!this.cpf.isEmpty()) {
			cliente.setCpf(cpf);
		}
		
		if(!this.email.isEmpty()) {
			cliente.setEmail(email);
		}
		
		if(!this.senha.isEmpty()) {
			cliente.setSenha(senha);
		}
		
		return cliente;
	}

}
