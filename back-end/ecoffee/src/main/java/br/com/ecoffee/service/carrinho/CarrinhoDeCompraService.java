package br.com.ecoffee.service.carrinho;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.model.carrinho.Carrinho;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.model.produto.Produto;
import br.com.ecoffee.repository.carrinho.CarrinhoRepository;
import br.com.ecoffee.service.cliente.ClienteService;
import br.com.ecoffee.service.produto.ProdutoService;

@Service
public class CarrinhoDeCompraService {

	private ClienteService clienteService;
	private ProdutoService produtoService;

	private CarrinhoRepository carrinhoRepository;

	public CarrinhoDeCompraService(ClienteService clienteService, ProdutoService produtoService,
			CarrinhoRepository carrinhoRepository) {
		this.clienteService = clienteService;
		this.produtoService = produtoService;
		this.carrinhoRepository = carrinhoRepository;
	}

	@Transactional
	public Carrinho inserirProduto(Long idProduto, Long idCliente, Integer quantidade) {

		/*Factory ?????*/
		Cliente cliente = clienteService.buscarClientePeloId(idCliente).get();
		Produto produto = produtoService.buscarProdutoPeloId(idProduto).get();
		
		Carrinho novoCarrinhoDeCompras = carrinhoRepository.save(new Carrinho(cliente, produto, quantidade));

		return novoCarrinhoDeCompras;
	}

}
