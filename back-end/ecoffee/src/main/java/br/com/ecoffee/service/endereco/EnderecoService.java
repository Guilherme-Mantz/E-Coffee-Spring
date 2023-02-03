package br.com.ecoffee.service.endereco;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ecoffee.dto.endereco.AtualizarEnderecoForm;
import br.com.ecoffee.dto.endereco.EnderecoDto;
import br.com.ecoffee.dto.endereco.EnderecoDtoMapper;
import br.com.ecoffee.dto.endereco.EnderecoForm;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.model.endereco.Endereco;
import br.com.ecoffee.repository.endereco.EnderecoRepository;
import br.com.ecoffee.service.cliente.ClienteService;

@Service
public class EnderecoService {
	
	private EnderecoRepository enderecoRepository;
	private ClienteService clienteService;
	
	public EnderecoService(EnderecoRepository enderecoRepository, ClienteService clienteService) {
		this.enderecoRepository = enderecoRepository;
		this.clienteService = clienteService;
	}

	public List<EnderecoDto> listarEnderecosPeloCliente(Long idCliente) {
		List<Endereco> enderecos = enderecoRepository.findByIdCliente(idCliente);
		
		List<EnderecoDto> dtos = enderecos.stream()
				.map(EnderecoDtoMapper.INSTANCE::toEnderecoDto)
				.collect(Collectors.toList());
		
		return dtos;
	}

	@Transactional
	public Endereco cadastrarEndereco(Long idCliente, EnderecoForm enderecoForm) {

		Cliente cliente = clienteService.buscarClientePeloId(idCliente).get();
		Endereco endereco = enderecoForm.toEndereco();
		endereco.setCliente(cliente);
		
		Endereco enderecoCadastrado = enderecoRepository.save(endereco);
		
		return enderecoCadastrado;
	}

	public Optional<Endereco> buscarEnderecoPeloIdClienteAndIdEndereco(Long idCliente,Long idEndereco) {
		return enderecoRepository.findByIdClienteAndIdEndereco(idCliente, idEndereco);
	}

	@Transactional
	public void deletarEndereco(Long idEndereco) {
		enderecoRepository.deleteById(idEndereco);
	}

	@Transactional
	public Endereco atualizarEndereco(Long idEndereco, AtualizarEnderecoForm atualizarForm) {
		
		Endereco endereco = enderecoRepository.findById(idEndereco).get();
		Endereco enderecoAtualizado = atualizarForm.atualizar(endereco);
		
		return enderecoAtualizado;
	}

	public Optional<Endereco> buscarEnderecoPeloId(Long idEndereco) {
		return enderecoRepository.findById(idEndereco);
	}

}
