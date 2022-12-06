package br.com.ecoffee.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoffee.model.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
