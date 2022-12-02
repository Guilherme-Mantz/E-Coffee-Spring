package br.com.ecoffee.service.cliente;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.cliente.AtualizarClienteForm;
import br.com.ecoffee.dto.cliente.ClienteForm;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.repository.cliente.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Transactional
	public Cliente cadastrarCliente(ClienteForm clienteForm) {
		Cliente cliente = clienteForm.toCliente();
		Cliente novoCliente = clienteRepository.save(cliente);
		
		return novoCliente;
	}

	public Optional<Cliente> buscarClientePeloEmail(String username) {
		return clienteRepository.findByEmail(username);
	}

	public Optional<Cliente> buscarClientePeloId(Long idUsuario) {
		return clienteRepository.findById(idUsuario);
	}

	@Transactional
	public Cliente atualizarCliente(Cliente cliente, AtualizarClienteForm clienteForm) {
		Cliente clienteAtualizado = clienteForm.atualizar(cliente);
		return clienteAtualizado;
	}

}
