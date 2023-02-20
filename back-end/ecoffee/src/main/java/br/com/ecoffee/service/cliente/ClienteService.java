package br.com.ecoffee.service.cliente;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.cliente.AtualizarClienteForm;
import br.com.ecoffee.dto.cliente.ClienteDto;
import br.com.ecoffee.dto.cliente.ClienteDtoMapper;
import br.com.ecoffee.dto.cliente.ClienteForm;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.repository.cliente.ClienteRepository;
import br.com.ecoffee.util.security.TokenService;
import br.com.ecoffee.validation.cliente.ClienteValidation;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	private ClienteValidation clienteValidation;
	private TokenService jwtService;

	public ClienteService(ClienteRepository clienteRepository, ClienteValidation clienteValidation,
			TokenService jwtService) {
		this.clienteRepository = clienteRepository;
		this.clienteValidation = clienteValidation;
		this.jwtService = jwtService;
	}

	public ClienteDto detalhesDeLoginCliente(String token) {
		Long idCliente = jwtService.getIdUsuario(token);
		Cliente cliente = clienteRepository.findById(idCliente).get();

		return ClienteDtoMapper.INSTANCE.toClienteDto(cliente);
	}

	@Transactional
	public ClienteDto cadastrarCliente(ClienteForm clienteForm) {
		Cliente cliente = clienteForm.toCliente();

		clienteValidation.validarEmailECpf(cliente);

		Cliente novoCliente = clienteRepository.save(cliente);

		return ClienteDtoMapper.INSTANCE.toClienteDto(novoCliente);
	}

	@Transactional
	public ClienteDto atualizarCliente(Cliente cliente, AtualizarClienteForm clienteForm) {

		clienteValidation.validarAtualizacaoEmailECpf(cliente, clienteForm);

		Cliente clienteAtualizado = clienteForm.atualizar(cliente);
		return ClienteDtoMapper.INSTANCE.toClienteDto(clienteAtualizado);
	}

	public Optional<Cliente> buscarClientePeloCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}

	public Optional<Cliente> buscarClientePeloEmail(String email) {
		return clienteRepository.findByEmail(email);
	}

	public Optional<Cliente> buscarClientePeloId(Long idUsuario) {
		return clienteRepository.findById(idUsuario);
	}

}
