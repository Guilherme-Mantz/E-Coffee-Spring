package br.com.ecoffee.repository.endereco;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ecoffee.model.endereco.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query("SELECT endereco FROM Endereco endereco WHERE endereco.cliente.idCliente = :idCliente")
	List<Endereco> findByIdCliente(Long idCliente);

	@Query("SELECT endereco FROM Endereco endereco WHERE endereco.idEndereco = :idEndereco AND endereco.cliente.idCliente = :idCliente")
	Optional<Endereco> findByIdClienteAndIdEndereco(Long idCliente, Long idEndereco);

}
