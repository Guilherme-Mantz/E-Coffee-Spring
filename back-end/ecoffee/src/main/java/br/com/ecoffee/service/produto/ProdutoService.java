package br.com.ecoffee.service.produto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.produto.DetalhesProdutoDto;
import br.com.ecoffee.dto.produto.ProdutoDto;
import br.com.ecoffee.dto.produto.ProdutoDtoMapper;
import br.com.ecoffee.model.produto.Produto;
import br.com.ecoffee.repository.produto.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<ProdutoDto> listarProdutosEmDestaque() {
		List<Produto> produtos = produtoRepository.findProdutos(); // Limitar para buscar 3 produtos de cada tipo
		List<ProdutoDto> emDestaque = produtos.stream()
				.map(ProdutoDtoMapper.INSTANCE::toProdutoDto)
				.collect(Collectors.toList());

		return emDestaque;
	}

	// Criar paginação
	public List<ProdutoDto> buscarProdutosPorCategoria(String categoria) {
		List<Produto> produtos = produtoRepository.findByNomeCategoria(categoria);
		List<ProdutoDto> produtosCategoria = produtos.stream()
				.map(ProdutoDtoMapper.INSTANCE::toProdutoDto)
				.collect(Collectors.toList());

		return produtosCategoria;
	}

	public Optional<Produto> buscarProdutoPeloId(Long idProduto) {
		return produtoRepository.findById(idProduto);
	}

	public DetalhesProdutoDto detalhesProduto(Long idProduto) {
		
		Produto produto = buscarProdutoPeloId(idProduto).get();
		
		return ProdutoDtoMapper.INSTANCE.toDetalhesProdutoDto(produto);
	}

}
