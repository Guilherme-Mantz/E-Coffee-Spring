package br.com.ecoffee.service.pedido;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.pedido.PedidoDetalhadoDto;
import br.com.ecoffee.dto.pedido.PedidoDto;
import br.com.ecoffee.dto.pedido.PedidoDtoMapper;
import br.com.ecoffee.factory.pedido.PedidoFactory;
import br.com.ecoffee.model.pedido.Pedido;
import br.com.ecoffee.repository.pedido.PedidoRepository;
import br.com.ecoffee.service.carrinho.CarrinhoDeCompraService;
import br.com.ecoffee.service.produto.ProdutoService;

@Service
public class PedidoService {

	private PedidoRepository pedidoRepository;
	private PedidoFactory pedidoFactory;

	private ProdutoService produtoService;
	private CarrinhoDeCompraService carrinhoService;

	public PedidoService(PedidoRepository pedidoRepository, PedidoFactory pedidoFactory, ProdutoService produtoService,
			CarrinhoDeCompraService carrinhoService) {
		this.pedidoRepository = pedidoRepository;
		this.pedidoFactory = pedidoFactory;
		this.produtoService = produtoService;
		this.carrinhoService = carrinhoService;
	}

	@Transactional
	public List<PedidoDto> finalizarCompra(Long idCliente, Long IdEndereco) {
		List<Pedido> pedidos = pedidoFactory.criarPedido(idCliente, IdEndereco);

		List<Pedido> novosPedidos = pedidoRepository.saveAll(pedidos);

		novosPedidos.forEach(p -> produtoService.subtrairQuantidadeDoEstoque(p.getProduto(), p.getQuantidade()));

		carrinhoService.deletarCarrinhosDoCliente(idCliente);

		return novosPedidos.stream().map(PedidoDtoMapper.INSTANCE::toPedidoDto).collect(Collectors.toList());
	}

	public List<PedidoDetalhadoDto> buscarPedidoPeloIdCliente(Long idCliente) {
		
		List<Pedido> pedidosDoCliente = pedidoRepository.findByIdCliente(idCliente);
		
		return pedidosDoCliente.stream().map(PedidoDtoMapper.INSTANCE::toPedidoDetalhadoDto).collect(Collectors.toList());
	}

}
