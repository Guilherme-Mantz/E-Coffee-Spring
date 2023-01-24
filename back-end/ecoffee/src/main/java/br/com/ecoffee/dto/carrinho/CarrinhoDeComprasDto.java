package br.com.ecoffee.dto.carrinho;

import br.com.ecoffee.model.carrinho.Carrinho;

public class CarrinhoDeComprasDto {

	private Long idCliente;
	private Long idProduto;
	private Long idCarrinho;
	private Integer quantidade;

	public CarrinhoDeComprasDto(Carrinho carrinhoDeCompra) {
		this.idCliente = carrinhoDeCompra.getCliente().getIdCliente();
		this.idProduto = carrinhoDeCompra.getProduto().getIdProduto();
		this.idCarrinho = carrinhoDeCompra.getIdCarrinho();
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
