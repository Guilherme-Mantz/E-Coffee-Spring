package br.com.ecoffee.dto.carrinho;

import br.com.ecoffee.model.carrinho.Carrinho;

public class CarrinhoDeComprasDto {

	private Long idCarrinho;
	private Long idCliente;
	private Long idProduto;
	private Integer quantidade;

	public CarrinhoDeComprasDto(Carrinho carrinhoDeCompra) {
		this.idCarrinho = carrinhoDeCompra.getIdCarrinho();
		this.idCliente = carrinhoDeCompra.getCliente().getIdCliente();
		this.idProduto = carrinhoDeCompra.getProduto().getIdProduto();
		this.quantidade = carrinhoDeCompra.getQuantidade();
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Long getIdCarrinho() {
		return idCarrinho;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

}
