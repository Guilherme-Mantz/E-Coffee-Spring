package br.com.ecoffee.dto.endereco;

import br.com.ecoffee.model.endereco.Endereco;

public class AtualizarEnderecoForm {

	private String nomeEndereco;
	private String logradouro;
	private String cep;
	private String numeroResidencia;
	private String bairro;
	private String cidade;
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

	public Endereco atualizar(Endereco endereco) {

		endereco.setNomeEndereco(nomeEndereco);

		endereco.setLogradouro(logradouro);

		endereco.setCep(cep);

		endereco.setNumeroResidencia(numeroResidencia);

		endereco.setBairro(bairro);

		endereco.setCidade(cidade);

		endereco.setUf(uf);

		endereco.setComplemento(complemento);

		return endereco;
	}

}
