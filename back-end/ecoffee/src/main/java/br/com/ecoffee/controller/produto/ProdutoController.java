package br.com.ecoffee.controller.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecoffee.dto.produto.DetalhesProdutoDto;
import br.com.ecoffee.dto.produto.ProdutoDto;
import br.com.ecoffee.model.produto.Produto;
import br.com.ecoffee.service.produto.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Cacheable("categorizados")
	@GetMapping("listar/{categoria}")
	public List<ProdutoDto> listarProdutosPorCategoria (@PathVariable String categoria) {
		List<ProdutoDto> produtosCategoria = produtoService.buscarProdutosPorCategoria(categoria);
		
		return produtosCategoria;
	}
	
	//implementar forma de deixar um produto em destaque para exibir no index
	@Cacheable("destaques")
	@GetMapping("listar/destaques")
	public List<ProdutoDto> listarProdutosEmDestaques(){ 
		List<ProdutoDto> produtos = produtoService.listarProdutosEmDestaque();
		
		return produtos;
	}
	
	@Cacheable("detalhes")
	@GetMapping("/{idProduto}")
	public ResponseEntity<DetalhesProdutoDto> listarInformacoesDoProduto (@PathVariable Long idProduto){
		
		Optional<Produto> produto = produtoService.buscarProdutoPeloId(idProduto);
		
		if(produto.isPresent()) {

			return ResponseEntity.ok(new DetalhesProdutoDto(produto.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
