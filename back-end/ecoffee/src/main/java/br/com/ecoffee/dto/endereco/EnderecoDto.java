package br.com.ecoffee.dto.endereco;

import br.com.ecoffee.model.endereco.Endereco;

public class EnderecoDto {

	private Long idEndereco;
	private String nomeEndereco;
	private String logradouro;
	private String cep;
	private String numeroResidencia;
	private String bairro;
	private String cidade;
	private String uf;
	private String complemento;

	public EnderecoDto(Endereco endereco) {
		this.idEndereco = endereco.getIdEndereco();
		this.nomeEndereco = endereco.getNomeEndereco();
		this.logradouro = endereco.getLogradouro();
		this.cep = endereco.getCep();
		this.numeroResidencia = endereco.getNumeroResidencia();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.uf = endereco.getUf();
		this.complemento = endereco.getComplemento();
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public String getNomeEndereco() {
		return nomeEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getCep() {
		return cep;
	}

	public String getNumeroResidencia() {
		return numeroResidencia;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUf() {
		return uf;
	}

	public String getComplemento() {
		return complemento;
	}

}
