package br.com.ecoffee.service.cliente;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.cliente.AtualizarClienteForm;
import br.com.ecoffee.dto.cliente.ClienteDto;
import br.com.ecoffee.dto.cliente.ClienteForm;
import br.com.ecoffee.exception.UniqueException;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.repository.cliente.ClienteRepository;
import br.com.ecoffee.util.security.TokenService;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	private TokenService jwtService;
	
	public ClienteService(ClienteRepository clienteRepository, TokenService jwtService) {
		this.clienteRepository = clienteRepository;
		this.jwtService = jwtService;
	}
	
	public ClienteDto detalhesDeLoginCliente(String token) {
	    Long idCliente = jwtService.getIdUsuario(token);
	    Optional<Cliente> cliente = clienteRepository.findById(idCliente);
	    
	    return new ClienteDto(cliente.get());
	}

	@Transactional
	public Cliente cadastrarCliente(ClienteForm clienteForm) {
		Cliente cliente = clienteForm.toCliente();
		
		if(buscarClientePeloEmail(cliente.getEmail()).isPresent()) {
			throw new UniqueException("E-mail já utilizado, por favor tente outro!", "email");
		}
		if(buscarClientePeloCpf(cliente.getCpf()).isPresent()) {
			throw new UniqueException("Por favor tente outro CPF!", "cpf");
		}
		
		Cliente novoCliente = clienteRepository.save(cliente);
		
		return novoCliente;
	}

	private Optional<Cliente> buscarClientePeloCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);		
	}

	public Optional<Cliente> buscarClientePeloEmail(String email) {
		return clienteRepository.findByEmail(email);
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
