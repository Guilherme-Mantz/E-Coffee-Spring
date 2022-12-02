package br.com.ecoffee.controller.endereco;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ecoffee.dto.endereco.EnderecoDto;
import br.com.ecoffee.dto.endereco.EnderecoForm;
import br.com.ecoffee.model.endereco.Endereco;
import br.com.ecoffee.service.endereco.EnderecoService;

@RestController
@RequestMapping("endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;

	@GetMapping("listar/{idCliente}")
	public List<EnderecoDto> listarEnderecosPeloIdCliente(@PathVariable Long idCliente) {
		
		List<EnderecoDto> enderecos = enderecoService.listarEnderecosPeloCliente(idCliente);
		return enderecos;
	}
	
	@PostMapping("cadastrar/{idCliente}")
	public ResponseEntity<EnderecoDto> cadastrarEnderecoPeloIdCliente(@PathVariable Long idCliente, @RequestBody @Valid EnderecoForm enderecoForm, UriComponentsBuilder uriBuilder){
		Endereco endereco = enderecoService.cadastrarEndereco(idCliente, enderecoForm);
		
		URI uri = uriBuilder.path("/endereco/cadastrar/{idEndereco}").buildAndExpand(endereco.getIdEndereco()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoDto(endereco));
	}
	
}
