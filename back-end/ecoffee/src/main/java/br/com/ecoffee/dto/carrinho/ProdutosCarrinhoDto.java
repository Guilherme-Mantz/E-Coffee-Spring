package br.com.ecoffee.dto.carrinho;

import java.math.BigDecimal;

import br.com.ecoffee.model.carrinho.Carrinho;

public class ProdutosCarrinhoDto {

	private Long idCarrinho;
	private Long idCliente;

	private Long idProduto;
	private String nomeProduto;
	private BigDecimal preco;
	private String imagem;

	private Integer quantidade;

	public ProdutosCarrinhoDto(Carrinho carrinhoDeCompra) {
		this.idCarrinho = carrinhoDeCompra.getIdCarrinho();
		this.idCliente = carrinhoDeCompra.getCliente().getIdCliente();
		this.idProduto = carrinhoDeCompra.getProduto().getIdProduto();
		this.nomeProduto = carrinhoDeCompra.getProduto().getNomeProduto();
		this.preco = carrinhoDeCompra.getProduto().getPreco();
		this.imagem = carrinhoDeCompra.getProduto().getImagem();
		this.quantidade = carrinhoDeCompra.getQuantidade();
	}

	public Long getIdCarrinho() {
		return idCarrinho;
	}

	public Long getIdCliente() {
		return idCliente;
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

	public Integer getQuantidade() {
		return quantidade;
	}

}
