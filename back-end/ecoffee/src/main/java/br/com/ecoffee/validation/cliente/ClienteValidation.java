package br.com.ecoffee.validation.cliente;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.ecoffee.dto.cliente.AtualizarClienteForm;
import br.com.ecoffee.exception.UniqueException;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.repository.cliente.ClienteRepository;

@Component
public class ClienteValidation {
	
	private final ClienteRepository repository;
	
	public ClienteValidation(ClienteRepository repository) {
		this.repository = repository ;
	}

	public void validarEmailECpf(Cliente cliente) {
		
		if(repository.findByEmail(cliente.getEmail()).isPresent()) {
			throw new UniqueException("E-mail já utilizado, por favor tente outro!", "email");
		}
		if(repository.findByCpf(cliente.getCpf()).isPresent()) {
			throw new UniqueException("Por favor tente outro CPF!", "cpf");
		}
		
	}

	public void validarAtualizacaoEmailECpf(Cliente cliente, AtualizarClienteForm clienteForm) {
		
		Optional<Cliente> emailCliente = repository.findByEmail(clienteForm.getEmail());
		
		if(emailCliente.isPresent() && emailCliente.get().getIdCliente() != cliente.getIdCliente()) {
			throw new UniqueException("E-mail já utilizado, por favor tente outro!", "email");
		}
		
		Optional<Cliente> cpfCliente = repository.findByCpf(clienteForm.getCpf());
		
		if(cpfCliente.isPresent() && cpfCliente.get().getIdCliente() != cliente.getIdCliente()) {
			throw new UniqueException("Por favor tente outro CPF!", "cpf");
		}
		
	}

}
