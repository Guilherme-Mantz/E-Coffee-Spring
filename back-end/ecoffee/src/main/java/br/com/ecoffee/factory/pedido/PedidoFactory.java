package br.com.ecoffee.factory.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ecoffee.model.carrinho.Carrinho;
import br.com.ecoffee.model.endereco.Endereco;
import br.com.ecoffee.model.pedido.Pedido;
import br.com.ecoffee.model.produto.Produto;
import br.com.ecoffee.service.carrinho.CarrinhoDeCompraService;
import br.com.ecoffee.service.endereco.EnderecoService;

@Service
public class PedidoFactory {

	private CarrinhoDeCompraService carrinhoService;
	private EnderecoService enderecoService;

	public PedidoFactory(CarrinhoDeCompraService carrinhoService, EnderecoService enderecoService) {
		this.carrinhoService = carrinhoService;
		this.enderecoService = enderecoService;
	}

	public List<Pedido> criarPedido(Long idCliente, Long idEndereco) {

		List<Pedido> pedidos = new ArrayList<>();

		List<Carrinho> carrinhos = carrinhoService.buscarCarrinhoPeloIdCliente(idCliente);
		Endereco endereco = enderecoService.buscarEnderecoPeloId(idEndereco).get();

		for (Carrinho cart : carrinhos) {

			Produto produto = cart.getProduto();
			Double valorTotal = cart.getQuantidade() * produto.getPreco().doubleValue();

			Pedido pedido = new Pedido(cart.getQuantidade(), LocalDateTime.now(), valorTotal, cart.getCliente(), endereco,
					produto);

			pedidos.add(pedido);
		}

		return pedidos;
	}

}
