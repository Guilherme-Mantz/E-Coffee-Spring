package br.com.ecoffee.service.cliente;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.cliente.AtualizarClienteForm;
import br.com.ecoffee.dto.cliente.ClienteForm;
import br.com.ecoffee.dto.cliente.LoginForm;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.repository.cliente.ClienteRepository;
import br.com.ecoffee.util.security.TokenService;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	private AuthenticationManager authManager;
	private TokenService tokenService;
	
	public ClienteService(ClienteRepository clienteRepository, AuthenticationManager authManager,
			TokenService tokenService) {
		this.clienteRepository = clienteRepository;
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	@Transactional
	public String login(LoginForm loginForm) {
		UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();
		
		Authentication authenticate = authManager.authenticate(dadosLogin);
		String token = tokenService.gerarToken(authenticate);
		
		return token;
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
