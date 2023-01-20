package br.com.ecoffee.dto.produto;

import java.math.BigDecimal;

import br.com.ecoffee.model.produto.Produto;

public class DetalhesProdutoDto {

	private Long idProduto;
	private String nomeProduto;
	private BigDecimal preco;
	private String marca;
	private String tituloPagina;
	private String descricaoProduto;
	private String informacoesTecnicas;
	private String imagem;

	public DetalhesProdutoDto(Produto produto) {
		this.idProduto = produto.getIdProduto();
		this.nomeProduto = produto.getNomeProduto();
		this.preco = produto.getPreco();
		this.marca = produto.getMarca().getMarca();
		this.tituloPagina = produto.getTituloPagina();
		this.descricaoProduto = produto.getDescricaoProduto();
		this.informacoesTecnicas = produto.getInformacoesTecnicas();
		this.imagem = produto.getImagem();
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getMarca() {
		return marca;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public String getInformacoesTecnicas() {
		return informacoesTecnicas;
	}

	public String getImagem() {
		return imagem;
	}

}
