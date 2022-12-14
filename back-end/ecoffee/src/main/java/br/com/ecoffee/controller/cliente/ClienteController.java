package br.com.ecoffee.controller.cliente;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ClienteService clienteService;

	@PostMapping("cadastrar")
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder) {
		Cliente cliente = clienteService.cadastrarCliente(clienteForm);
		
		URI uri = uriBuilder.path("/cliente/{idCliente}").buildAndExpand(cliente.getIdCliente()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}
	
	@PutMapping("atualizar/{id}")
	public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @RequestBody AtualizarClienteForm clienteForm) {
		Optional<Cliente> cliente = clienteService.buscarClientePeloId(id);
		if(cliente.isPresent()) {
			
			Cliente clienteAtualizado = clienteService.atualizarCliente(cliente.get(), clienteForm);
			return ResponseEntity.ok(new ClienteDto(clienteAtualizado));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("get/data")
	public ClienteDto detalhesDeLoginCliente(@RequestHeader("Authorization") String authorization) {
		
		String token = authorization.replace("Bearer ", "");
		ClienteDto cliente = clienteService.detalhesDeLoginCliente(token);
		
		return cliente;
	}
	
}
