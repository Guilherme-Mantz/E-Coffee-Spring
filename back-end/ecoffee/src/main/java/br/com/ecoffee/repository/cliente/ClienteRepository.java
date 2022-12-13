package br.com.ecoffee.repository.cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoffee.model.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByEmail(String email);

	Optional<Cliente> findByCpf(String cpf);

}
