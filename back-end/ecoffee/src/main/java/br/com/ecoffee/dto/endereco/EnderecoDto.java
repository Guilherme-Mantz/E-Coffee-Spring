package br.com.ecoffee.dto.endereco;

import org.springframework.stereotype.Component;

import br.com.ecoffee.model.endereco.Endereco;

@Component
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

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

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

	public EnderecoDto toEnderecoDto(Endereco endereco) {
		return EnderecoDtoMapper.INSTANCE.toEnderecoDto(endereco);
	}

}
