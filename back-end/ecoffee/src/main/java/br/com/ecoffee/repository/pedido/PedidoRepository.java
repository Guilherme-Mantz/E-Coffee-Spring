package br.com.ecoffee.repository.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ecoffee.model.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("SELECT pedido FROM Pedido pedido WHERE pedido.cliente.idCliente = :idCliente")
	List<Pedido> findByIdCliente(Long idCliente);

}
