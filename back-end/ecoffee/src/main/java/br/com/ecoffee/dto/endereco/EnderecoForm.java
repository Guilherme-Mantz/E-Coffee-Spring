package br.com.ecoffee.dto.endereco;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.ecoffee.model.endereco.Endereco;

public class EnderecoForm {

	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String nomeEndereco;

	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String logradouro;

	@NotNull
	@NotEmpty
	@Length(min = 8)
	private String cep;

	@NotNull
	@NotEmpty
	@Length(min = 1)
	private String numeroResidencia;

	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String bairro;

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String cidade;

	@NotNull
	@NotEmpty
	@Length(min = 2)
	private String uf;
	private String complemento;

	public String getNomeEndereco() {
		return nomeEndereco;
	}

	public void setNomeEndereco(String nomeEndereco) {
		this.nomeEndereco = nomeEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumeroResidencia() {
		return numeroResidencia;
	}

	public void setNumeroResidencia(String numeroResidencia) {
		this.numeroResidencia = numeroResidencia;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Endereco toEndereco() {
		return new Endereco(nomeEndereco, logradouro, cep, numeroResidencia, bairro, cidade, uf, complemento);
	}

}
