package br.com.ecoffee.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoffee.model.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
