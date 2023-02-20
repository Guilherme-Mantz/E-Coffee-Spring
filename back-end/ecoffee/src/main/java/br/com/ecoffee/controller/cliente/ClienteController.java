package br.com.ecoffee.controller.cliente;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ecoffee.dto.cliente.AtualizarClienteForm;
import br.com.ecoffee.dto.cliente.ClienteDto;
import br.com.ecoffee.dto.cliente.ClienteForm;
import br.com.ecoffee.model.cliente.Cliente;
import br.com.ecoffee.service.cliente.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping("cadastrar")
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder) {
		ClienteDto cliente = clienteService.cadastrarCliente(clienteForm);
		
		URI uri = uriBuilder.path("/cliente/{idCliente}").buildAndExpand(cliente.getIdCliente()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@CacheEvict(value="dataCliente", allEntries = true)
	@PutMapping("atualizar/{id}")
	public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @RequestBody @Valid AtualizarClienteForm clienteForm) {
		Optional<Cliente> cliente = clienteService.buscarClientePeloId(id);
		if(cliente.isPresent()) {
			
			ClienteDto clienteAtualizado = clienteService.atualizarCliente(cliente.get(), clienteForm);
			return ResponseEntity.ok(clienteAtualizado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@Cacheable("dataCliente")
	@GetMapping("get/data")
	public ClienteDto detalhesDeLoginCliente(@RequestHeader("Authorization") String authorization) {
		
		String token = authorization.replace("Bearer ", "");
		ClienteDto cliente = clienteService.detalhesDeLoginCliente(token);
		
		return cliente;
	}
	
}
