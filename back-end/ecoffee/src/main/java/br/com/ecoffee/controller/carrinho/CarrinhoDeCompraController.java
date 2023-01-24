package br.com.ecoffee.controller.carrinho;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ecoffee.dto.carrinho.CarrinhoDeComprasDto;
import br.com.ecoffee.dto.carrinho.QuantidadeDto;
import br.com.ecoffee.model.carrinho.Carrinho;
import br.com.ecoffee.service.carrinho.CarrinhoDeCompraService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoDeCompraController {

	@Autowired
	private CarrinhoDeCompraService carrinhoService;

	@PostMapping("inserirproduto/{idProduto}/{idCliente}")
	public ResponseEntity<CarrinhoDeComprasDto> inserirProdutoNoCarrinho(@PathVariable Long idProduto, @PathVariable Long idCliente,
			@RequestBody QuantidadeDto quantidade, UriComponentsBuilder uriBuilder) {

		Carrinho carrinhoDeCompra = carrinhoService.inserirProduto(idProduto, idCliente, quantidade.getQuantidade());
		
		URI uri = uriBuilder.path("/cliente/{idCliente}").buildAndExpand(carrinhoDeCompra.getIdCarrinho()).toUri();
		return ResponseEntity.created(uri).body(new CarrinhoDeComprasDto(carrinhoDeCompra));
	}
}