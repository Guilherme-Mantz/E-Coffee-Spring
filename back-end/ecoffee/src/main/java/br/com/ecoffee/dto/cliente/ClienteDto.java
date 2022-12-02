package br.com.ecoffee.dto.cliente;

import br.com.ecoffee.model.cliente.Cliente;

public class ClienteDto {

	private Long idCliente;
	private String nome;
	private String sobrenome;

	public ClienteDto(Cliente cliente) {
		this.idCliente = cliente.getIdCliente();
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
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

}
