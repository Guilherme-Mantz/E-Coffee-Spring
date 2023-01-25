package br.com.ecoffee.service.carrinho;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.carrinho.ProdutosCarrinhoDto;
import br.com.ecoffee.factory.carrinho.CarrinhoDeCompraFactory;
import br.com.ecoffee.model.carrinho.Carrinho;
import br.com.ecoffee.repository.carrinho.CarrinhoRepository;

@Service
public class CarrinhoDeCompraService {

	private CarrinhoRepository carrinhoRepository;
	private CarrinhoDeCompraFactory carrinhoFactory;

	public CarrinhoDeCompraService(CarrinhoRepository carrinhoRepository, CarrinhoDeCompraFactory carrinhoFactory) {
		this.carrinhoRepository = carrinhoRepository;
		this.carrinhoFactory = carrinhoFactory;
	}

	@Transactional
	public Carrinho inserirProduto(Long idProduto, Long idCliente, Integer quantidade) {

		Carrinho carrinhoDeCompra = carrinhoFactory.criarCarrinhoDeCompra(idProduto, idCliente, quantidade);

		Carrinho novoCarrinhoDeCompras = carrinhoRepository.save(carrinhoDeCompra);

		return novoCarrinhoDeCompras;
	}

	@Transactional
	public void deletarCarrinho(Long idCarrinho) {
		carrinhoRepository.deleteById(idCarrinho);
	}

	public List<ProdutosCarrinhoDto> buscarProdutosNoCarrinho(Long idCliente) {

		List<ProdutosCarrinhoDto> produtos = carrinhoRepository.findProdutosByIdCliente(idCliente);
		return produtos;
	}

	public Optional<Carrinho> buscarCarrinhoPeloId(Long idCarrinho) {
		return carrinhoRepository.findById(idCarrinho);
	}

}
