package br.com.ecoffee.dto.pedido;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.ecoffee.model.pedido.Pedido;

@Mapper(componentModel = "spring")
public interface PedidoDtoMapper {

	PedidoDtoMapper INSTANCE = Mappers.getMapper(PedidoDtoMapper.class);

	@Mapping(target = "idCliente", source = "pedido.cliente.idCliente")
	@Mapping(target = "idProduto", source = "pedido.produto.idProduto")
	@Mapping(target = "idEndereco", source = "pedido.endereco.idEndereco")
	PedidoDto toPedidoDto(Pedido pedido);
	
	@Mapping(target = "nomeProduto", source = "pedido.produto.nomeProduto")
	@Mapping(target = "imagem", source = "pedido.produto.imagem")
	PedidoDetalhadoDto toPedidoDetalhadoDto(Pedido pedido);

}
