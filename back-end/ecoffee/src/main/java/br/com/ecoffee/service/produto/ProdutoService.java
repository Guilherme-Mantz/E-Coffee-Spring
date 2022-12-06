package br.com.ecoffee.service.produto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.produto.ProdutoDto;
import br.com.ecoffee.model.produto.Produto;
import br.com.ecoffee.repository.produto.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<ProdutoDto> listarProdutos() {
		//Criar Paginação
		List<Produto> todosOsProdutos = produtoRepository.findAll();
		List<ProdutoDto> produtosDtos = todosOsProdutos.stream().map(ProdutoDto::new).collect(Collectors.toList());
		
		return produtosDtos;
	}

}
