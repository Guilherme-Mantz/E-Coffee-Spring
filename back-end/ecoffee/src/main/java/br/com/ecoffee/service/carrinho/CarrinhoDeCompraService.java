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
import br.com.ecoffee.validation.carrinho.CarrinhoValidation;

@Service
public class CarrinhoDeCompraService {

	private final CarrinhoRepository carrinhoRepository;
	private final CarrinhoDeCompraFactory carrinhoFactory;
	private final CarrinhoValidation carrinhoValidation;

	public CarrinhoDeCompraService(CarrinhoRepository carrinhoRepository, CarrinhoDeCompraFactory carrinhoFactory,
			CarrinhoValidation carrinhoValidation) {
		this.carrinhoRepository = carrinhoRepository;
		this.carrinhoFactory = carrinhoFactory;
		this.carrinhoValidation = carrinhoValidation;
	}

	@Transactional
	public CarrinhoDeComprasDto inserirProduto(Long idProduto, Long idCliente, Integer quantidade) {
		
		CarrinhoDeComprasDto carrinhoValidado = carrinhoValidation.validarProdutoNoCarrinho(idProduto, idCliente, quantidade);
		
		if(carrinhoValidado != null) {
			return carrinhoValidado;
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
				.map(CarrinhoDtoMapper.INSTANCE::toProdutosCarrinhoDto).collect(Collectors.toList());

		return produtos;
	}

	public List<Carrinho> buscarCarrinhoPeloIdCliente(Long idCliente) {
		List<Carrinho> carrinhosDeCompra = carrinhoRepository.findByIdCliente(idCliente);
		return carrinhosDeCompra;
	}

	public Optional<Carrinho> buscarCarrinhoPeloId(Long idCarrinho) {
		return carrinhoRepository.findById(idCarrinho);
	}

}
