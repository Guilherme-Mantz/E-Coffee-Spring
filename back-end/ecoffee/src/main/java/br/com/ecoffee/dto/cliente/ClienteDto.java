package br.com.ecoffee.dto.cliente;

import br.com.ecoffee.model.cliente.Cliente;

public class ClienteDto {

	private Long idCliente;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String cpf;
	private String email;

	public ClienteDto(Cliente cliente) {
		this.idCliente = cliente.getIdCliente();
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.telefone = cliente.getTelefone();
		this.cpf = cliente.getCpf();
		this.email = cliente.getEmail();
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

}
