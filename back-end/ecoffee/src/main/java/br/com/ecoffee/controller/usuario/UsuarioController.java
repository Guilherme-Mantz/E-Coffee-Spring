package br.com.ecoffee.controller.usuario;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ecoffee.dto.usuario.UsuarioDto;
import br.com.ecoffee.dto.usuario.UsuarioForm;
import br.com.ecoffee.service.usuario.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("cadastrar")
	public ResponseEntity<UsuarioDto> cadastrarCliente(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
		UsuarioDto usuario = usuarioService.cadastrarUsuario(usuarioForm);
		
		URI uri = uriBuilder.path("/usuario/{idUsuario}").buildAndExpand(usuario.getIdUsuario()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}

}
