package br.com.ecoffee.validation.carrinho;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.ecoffee.dto.carrinho.CarrinhoDeComprasDto;
import br.com.ecoffee.dto.carrinho.CarrinhoDtoMapper;
import br.com.ecoffee.model.carrinho.Carrinho;
import br.com.ecoffee.repository.carrinho.CarrinhoRepository;

@Component
public class CarrinhoValidation {
	
	private final CarrinhoRepository carrinhoRepository;
	
	public CarrinhoValidation(CarrinhoRepository carrinhoRepository) {
		this.carrinhoRepository = carrinhoRepository;
	}

	@Transactional
	public CarrinhoDeComprasDto validarProdutoNoCarrinho(Long idProduto, Long idCliente, Integer quantidade) {
		
		Optional<Carrinho> carrinhoCliente = carrinhoRepository.findByIdProdutoAndIdCliente(idCliente, idProduto);
		
		if(carrinhoCliente.isPresent()) {
			
			carrinhoCliente.get().adicionarQuantidade(quantidade);
			
			Carrinho quantidadeAtualizada = carrinhoRepository.save(carrinhoCliente.get());
			CarrinhoDeComprasDto carrinho = CarrinhoDtoMapper.INSTANCE.toCarrinhoDeCompraDto(quantidadeAtualizada);
			
			return carrinho;
		}
		
		return null;
		
	}

}
