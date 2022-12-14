package br.com.ecoffee.dto.produto;

import java.math.BigDecimal;

import br.com.ecoffee.model.produto.Produto;

public class ProdutoDto {

	private Long idProduto;
	private String nomeProduto;
	private BigDecimal preco;
	private String imagem;

	public ProdutoDto(Produto produto) {
		this.idProduto = produto.getIdProduto();
		this.nomeProduto = produto.getNomeProduto();
		this.preco = produto.getPreco();
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

	public String getImagem() {
		return imagem;
	}

}
