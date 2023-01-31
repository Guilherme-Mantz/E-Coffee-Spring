package br.com.ecoffee.repository.carrinho;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ecoffee.model.carrinho.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

	@Query("SELECT carrinho FROM Carrinho carrinho WHERE carrinho.cliente.idCliente = :idCliente")
	List<Carrinho> findProdutosByIdCliente(Long idCliente);

}
