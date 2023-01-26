package br.com.ecoffee.controller.carrinho;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ecoffee.dto.carrinho.CarrinhoDeComprasDto;
import br.com.ecoffee.dto.carrinho.ProdutosCarrinhoDto;
import br.com.ecoffee.dto.carrinho.QuantidadeDto;
import br.com.ecoffee.model.carrinho.Carrinho;
import br.com.ecoffee.service.carrinho.CarrinhoDeCompraService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoDeCompraController {

	@Autowired
	private CarrinhoDeCompraService carrinhoService;
	
	@GetMapping("getdata/{idCliente}")
	public List<ProdutosCarrinhoDto> buscarProdutosNoCarrinho(@PathVariable Long idCliente){
		
		List<ProdutosCarrinhoDto> produtos = carrinhoService.buscarProdutosNoCarrinho(idCliente);
		
		return produtos;
	}

	@PostMapping("inserirproduto/{idProduto}/{idCliente}")
	public ResponseEntity<CarrinhoDeComprasDto> inserirProdutoNoCarrinho(@PathVariable Long idProduto, @PathVariable Long idCliente,
			@RequestBody QuantidadeDto quantidade, UriComponentsBuilder uriBuilder) {

		CarrinhoDeComprasDto carrinhoDeCompra = carrinhoService.inserirProduto(idProduto, idCliente, quantidade.getQuantidade());
		
		URI uri = uriBuilder.path("/carrinho/inserirproduto/{idCarrinho}").buildAndExpand(carrinhoDeCompra.getIdCarrinho()).toUri();
		return ResponseEntity.created(uri).body(carrinhoDeCompra);
	}
	
	@DeleteMapping("removerproduto/{idCarrinho}")
	public ResponseEntity<?> deletarProdutoDoCarrinho(@PathVariable Long idCarrinho){
		
		Optional<Carrinho> carrinhoDeCompra = carrinhoService.buscarCarrinhoPeloId(idCarrinho);
		
		if(carrinhoDeCompra.isPresent()) {
			
			carrinhoService.deletarCarrinho(idCarrinho);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
