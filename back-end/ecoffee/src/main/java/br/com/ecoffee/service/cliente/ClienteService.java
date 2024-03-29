package br.com.ecoffee.service.cliente;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.cliente.AtualizarClienteForm;
import br.com.ecoffee.dto.cliente.ClienteDto;
import br.com.ecoffee.dto.cliente.ClienteDtoMapper;
import br.com.ecoffee.dto.cliente.ClienteForm;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.model.role.Role;
import br.com.ecoffee.repository.cliente.ClienteRepository;
import br.com.ecoffee.repository.role.RoleRepository;
import br.com.ecoffee.util.security.JwtTokenUtil;
import br.com.ecoffee.validation.cliente.ClienteValidation;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	private ClienteValidation clienteValidation;
	private RoleRepository roleRepository;
	private JwtTokenUtil jwtTokenUtil;

	public ClienteService(ClienteRepository clienteRepository, ClienteValidation clienteValidation,
			RoleRepository roleRepository, JwtTokenUtil jwtTokenUtil) {
		this.clienteRepository = clienteRepository;
		this.clienteValidation = clienteValidation;
		this.roleRepository = roleRepository;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	public ClienteDto detalhesDeLoginCliente(String token) {
		String username = jwtTokenUtil.getUsername(token);
		Cliente cliente = buscarClientePeloEmail(username).get();

		return ClienteDtoMapper.INSTANCE.toClienteDto(cliente);
	}

	@Transactional
	public ClienteDto cadastrarCliente(ClienteForm clienteForm) {
		Cliente cliente = clienteForm.toCliente();

		clienteValidation.validarEmailECpf(cliente);

		Role userRole = roleRepository.findByName("USER");
		cliente.setRoles(userRole);

		Cliente novoCliente = clienteRepository.save(cliente);

		return ClienteDtoMapper.INSTANCE.toClienteDto(novoCliente);
	}

	@Transactional
	public ClienteDto atualizarCliente(Cliente cliente, AtualizarClienteForm clienteForm) {

		clienteValidation.validarAtualizacaoEmailECpf(cliente, clienteForm);

		Cliente clienteAtualizado = clienteForm.atualizar(cliente);
		return ClienteDtoMapper.INSTANCE.toClienteDto(clienteAtualizado);
	}

	public Optional<Cliente> buscarClientePeloEmail(String email) {
		return clienteRepository.findByEmail(email);
	}

	public Optional<Cliente> buscarClientePeloId(Long idUsuario) {
		return clienteRepository.findById(idUsuario);
	}

}
