package br.com.ecoffee.controller.endereco;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ecoffee.dto.endereco.AtualizarEnderecoForm;
import br.com.ecoffee.dto.endereco.EnderecoDto;
import br.com.ecoffee.dto.endereco.EnderecoForm;
import br.com.ecoffee.model.endereco.Endereco;
import br.com.ecoffee.service.endereco.EnderecoService;

@RestController
@RequestMapping("endereco")
public class EnderecoController {
	
	private EnderecoService enderecoService;
	private EnderecoDto enderecoDto;
	
	public EnderecoController(EnderecoService enderecoService, EnderecoDto enderecoDto) {
		this.enderecoService = enderecoService;
		this.enderecoDto = enderecoDto;
	}

	@GetMapping("listar/{idCliente}")
	public List<EnderecoDto> listarEnderecosPeloIdCliente(@PathVariable Long idCliente) {
		
		List<EnderecoDto> enderecos = enderecoService.listarEnderecosPeloCliente(idCliente);
		return enderecos;
	}
	
	@GetMapping("buscar/{idCliente}/{idEndereco}")
	public EnderecoDto buscarEnderecoPeloIdClienteAndIdEndereco(@PathVariable Long idCliente, @PathVariable Long idEndereco) {
		
		Endereco enderecoEncontrado = enderecoService.buscarEnderecoPeloIdClienteAndIdEndereco(idCliente, idEndereco).get();
		EnderecoDto endereco = enderecoDto.toEnderecoDto(enderecoEncontrado);
		
		return endereco;
	}
	
	@PostMapping("cadastrar/{idCliente}")
	public ResponseEntity<EnderecoDto> cadastrarEnderecoPeloIdCliente(@PathVariable Long idCliente, @RequestBody @Valid EnderecoForm enderecoForm, UriComponentsBuilder uriBuilder){
		Endereco endereco = enderecoService.cadastrarEndereco(idCliente, enderecoForm);
		
		URI uri = uriBuilder.path("/endereco/cadastrar/{idEndereco}").buildAndExpand(endereco.getIdEndereco()).toUri();
		return ResponseEntity.created(uri).body(enderecoDto.toEnderecoDto(endereco));
	}
	
	@PutMapping("atualizar/{idEndereco}")
	public ResponseEntity<EnderecoDto> atualizarEnderecoPeloId(@PathVariable Long idEndereco, @RequestBody @Valid AtualizarEnderecoForm atualizarForm){
		
		Endereco enderecoAtualizado = enderecoService.atualizarEndereco(idEndereco, atualizarForm);
		return ResponseEntity.ok(enderecoDto.toEnderecoDto(enderecoAtualizado));
		
	}
	
	@DeleteMapping("deletar/{idCliente}/{idEndereco}")
	public ResponseEntity<?> deletarEnderecoPeloId(@PathVariable Long idCliente,@PathVariable Long idEndereco){
		
		Optional<Endereco> endereco = enderecoService.buscarEnderecoPeloIdClienteAndIdEndereco(idCliente ,idEndereco);
		if(endereco.isPresent()) {
			
			enderecoService.deletarEndereco(idEndereco);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
