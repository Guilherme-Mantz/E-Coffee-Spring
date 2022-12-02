package br.com.ecoffee.service.security.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.service.cliente.ClienteService;

@Service
public class AutenticacaoService implements UserDetailsService {

	private ClienteService clienteService;

	public AutenticacaoService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Cliente> cliente = clienteService.buscarClientePeloEmail(username);

		if(cliente.isPresent()) {
			return cliente.get();
		}
		
		throw new UsernameNotFoundException("Dados invalidos");
	}

}
