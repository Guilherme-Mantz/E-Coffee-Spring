package br.com.ecoffee.factory.carrinho;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.exception.BusinessException;
import br.com.ecoffee.model.carrinho.Carrinho;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.model.produto.Produto;
import br.com.ecoffee.service.cliente.ClienteService;
import br.com.ecoffee.service.produto.ProdutoService;

@Service
public class CarrinhoDeCompraFactory {
	
	private ClienteService clienteService;
	private ProdutoService produtoService;
	
	public CarrinhoDeCompraFactory(ClienteService clienteService, ProdutoService produtoService) {
		this.clienteService = clienteService;
		this.produtoService = produtoService;
	}

	public Carrinho criarCarrinhoDeCompra(Long idProduto, Long idCliente, Integer quantidade) {
		
		Cliente cliente = buscarClientePeloId(idCliente);		
		Produto produto = buscarProdutoPeloId(idProduto);
		
		return new Carrinho(cliente, produto, quantidade);
	}
	
	private Cliente buscarClientePeloId(Long idCliente) {
		Optional<Cliente> cliente = clienteService.buscarClientePeloId(idCliente);
		return cliente.orElseThrow(() -> new BusinessException("Cliente não encontrado!"));
	}

	private Produto buscarProdutoPeloId(Long idProduto) {
		Optional<Produto> produto = produtoService.buscarProdutoPeloId(idProduto);
		return produto.orElseThrow(() -> new BusinessException("Produto não encontrado!"));
	}
}
