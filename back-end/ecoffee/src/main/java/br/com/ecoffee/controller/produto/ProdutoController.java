package br.com.ecoffee.controller.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecoffee.dto.produto.ProdutoDto;
import br.com.ecoffee.service.produto.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;

	@GetMapping("listar")
	public List<ProdutoDto> listarProdutos(){
		List<ProdutoDto> produtos = produtoService.listarProdutos();
		return produtos;
	}
	
}
