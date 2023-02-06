package br.com.ecoffee.repository.carrinho;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.ecoffee.model.carrinho.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

	@Query("SELECT carrinho FROM Carrinho carrinho WHERE carrinho.cliente.idCliente = :idCliente")
	List<Carrinho> findByIdCliente(Long idCliente);

	@Modifying
	@Query("DELETE FROM Carrinho carrinho WHERE carrinho.cliente.idCliente = :idCliente")
	void deleteAllByIdCliente(Long idCliente);

	@Query("SELECT carrinho FROM Carrinho carrinho WHERE carrinho.cliente.idCliente = :idCliente AND carrinho.produto.idProduto = :idProduto")
	Optional<Carrinho> findByIdProdutoAndIdCliente(Long idCliente, Long idProduto);

}
