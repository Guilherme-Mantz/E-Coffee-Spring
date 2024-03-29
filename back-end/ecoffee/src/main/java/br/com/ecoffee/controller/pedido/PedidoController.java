package br.com.ecoffee.controller.pedido;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ecoffee.dto.pedido.FormPedido;
import br.com.ecoffee.dto.pedido.PedidoDetalhadoDto;
import br.com.ecoffee.dto.pedido.PedidoDto;
import br.com.ecoffee.service.pedido.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping("finalizarcompra")
	public ResponseEntity<List<PedidoDto>> finalizarCompra(@RequestBody FormPedido formPedido, UriComponentsBuilder uriBuilder) {

		List<PedidoDto> pedidosSalvos = pedidoService.finalizarCompra(formPedido.getIdCliente(),
				formPedido.getIdEndereco());

		URI uri = uriBuilder.path("/pedido/finalziarcompra").buildAndExpand().toUri();
		return ResponseEntity.created(uri).body(pedidosSalvos);
	}
	
	@GetMapping("getpedidos/{idCliente}")
	public List<PedidoDetalhadoDto> buscarPedidos(@PathVariable Long idCliente) {
		
		List<PedidoDetalhadoDto> pedidosDoCliente = pedidoService.buscarPedidoPeloIdCliente(idCliente);
		
		return pedidosDoCliente;
		
	}
}
