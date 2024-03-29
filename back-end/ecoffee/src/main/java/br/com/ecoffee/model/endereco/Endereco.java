package br.com.ecoffee.model.endereco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ecoffee.model.cliente.Cliente;

@Entity
@Table(name = "enderecos")
public class Endereco {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADRES_SEQ")
    @SequenceGenerator(name="ADRES_SEQ", sequenceName="ADRES_SEQ", allocationSize=100)
	@Column(columnDefinition = "serial")
	private Long idEndereco;

	@Column(name = "nome_do_endereco", nullable = false, length = 45)
	private String nomeEndereco;

	@Column(nullable = false, length = 255)
	private String logradouro;

	@Column(nullable = false, length = 8)
	private String cep;

	@Column(name = "numero_residencia", nullable = false, length = 10)
	private String numeroResidencia;

	@Column(nullable = false, length = 200)
	private String bairro;

	@Column(nullable = false, length = 200)
	private String cidade;

	@Column(nullable = false, length = 2)
	private String uf;

	@Column(nullable = true, length = 200)
	private String complemento;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;
	
	public Endereco() {}

	public Endereco(String nomeEndereco, String logradouro, String cep, String numeroResidencia, String bairro,
			String cidade, String uf, String complemento) {
		this.nomeEndereco = nomeEndereco;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numeroResidencia = numeroResidencia;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.complemento = complemento;
	}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
