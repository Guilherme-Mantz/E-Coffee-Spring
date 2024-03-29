package br.com.ecoffee.dto.produto;

import java.math.BigDecimal;

public class DetalhesProdutoDto {

	private Long idProduto;
	private String nomeProduto;
	private BigDecimal preco;
	private String marca;
	private String tituloPagina;
	private String descricaoProduto;
	private String informacoesTecnicas;
	private String imagem;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public String getInformacoesTecnicas() {
		return informacoesTecnicas;
	}

	public void setInformacoesTecnicas(String informacoesTecnicas) {
		this.informacoesTecnicas = informacoesTecnicas;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
