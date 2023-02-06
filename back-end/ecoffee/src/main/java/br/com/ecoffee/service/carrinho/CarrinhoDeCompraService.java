package br.com.ecoffee.service.carrinho;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.carrinho.CarrinhoDeComprasDto;
import br.com.ecoffee.dto.carrinho.CarrinhoDtoMapper;
import br.com.ecoffee.dto.carrinho.ProdutosCarrinhoDto;
import br.com.ecoffee.factory.carrinho.CarrinhoDeCompraFactory;
import br.com.ecoffee.model.carrinho.Carrinho;
import br.com.ecoffee.repository.carrinho.CarrinhoRepository;

@Service
public class CarrinhoDeCompraService {

	private final CarrinhoRepository carrinhoRepository;
	private final CarrinhoDeCompraFactory carrinhoFactory;

	public CarrinhoDeCompraService(CarrinhoRepository carrinhoRepository, CarrinhoDeCompraFactory carrinhoFactory) {
		this.carrinhoRepository = carrinhoRepository;
		this.carrinhoFactory = carrinhoFactory;
	}

	@Transactional
	public CarrinhoDeComprasDto inserirProduto(Long idProduto, Long idCliente, Integer quantidade) {
		
		Optional<Carrinho> validacao = this.buscarCarrinhoPeloIdProdutoAndIdCliente(idProduto, idCliente);
		if(validacao.isPresent()) {
			
			validacao.get().adicionarQuantidade(quantidade);
			
			Carrinho quantidadeAtualizada = carrinhoRepository.save(validacao.get());
			CarrinhoDeComprasDto carrinho = CarrinhoDtoMapper.INSTANCE.toCarrinhoDeCompraDto(quantidadeAtualizada);
			
			return carrinho;
		}

		Carrinho novoCarrinhoDeCompras = carrinhoRepository
				.save(carrinhoFactory.criarCarrinhoDeCompra(idProduto, idCliente, quantidade));

		CarrinhoDeComprasDto carrinho = CarrinhoDtoMapper.INSTANCE.toCarrinhoDeCompraDto(novoCarrinhoDeCompras);

		return carrinho;
	}

	@Transactional
	public void deletarCarrinho(Long idCarrinho) {
		carrinhoRepository.deleteById(idCarrinho);
	}
	
	@Transactional
	public void deletarCarrinhosDoCliente(Long idCliente) {
		carrinhoRepository.deleteAllByIdCliente(idCliente);
	}

	public List<ProdutosCarrinhoDto> buscarProdutosNoCarrinho(Long idCliente) {

		List<Carrinho> carrinhosDeCompra = carrinhoRepository.findByIdCliente(idCliente);

		List<ProdutosCarrinhoDto> produtos = carrinhosDeCompra.stream()
				.map(CarrinhoDtoMapper.INSTANCE::toProdutosCarrinhoDto)
				.collect(Collectors.toList());

		return produtos;
	}
	
	public List<Carrinho> buscarCarrinhoPeloIdCliente(Long idCliente){
		List<Carrinho> carrinhosDeCompra = carrinhoRepository.findByIdCliente(idCliente);
		return carrinhosDeCompra;
	}

	public Optional<Carrinho> buscarCarrinhoPeloId(Long idCarrinho) {
		return carrinhoRepository.findById(idCarrinho);
	}
	
	public Optional<Carrinho> buscarCarrinhoPeloIdProdutoAndIdCliente (Long idProduto, Long idCliente){
		return carrinhoRepository.findByIdProdutoAndIdCliente(idCliente, idProduto);
	}

}
