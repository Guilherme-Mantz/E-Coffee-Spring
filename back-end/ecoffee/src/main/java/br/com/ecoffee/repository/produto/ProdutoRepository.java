package br.com.ecoffee.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ecoffee.model.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	@Query("SELECT produto FROM Produto produto WHERE produto.categoria.nome = :categoria")
	List<Produto> findByNomeCategoria(String categoria);

}
