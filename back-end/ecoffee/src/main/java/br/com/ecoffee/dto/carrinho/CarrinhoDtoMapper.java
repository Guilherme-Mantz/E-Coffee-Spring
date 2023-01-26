package br.com.ecoffee.dto.carrinho;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.ecoffee.model.carrinho.Carrinho;

@Mapper(componentModel = "spring")
public interface CarrinhoDtoMapper {

	CarrinhoDtoMapper INSTANCE = Mappers.getMapper(CarrinhoDtoMapper.class);

	@Mapping(target = "idCliente", source = "carrinho.cliente.idCliente")
	@Mapping(target = "idProduto", source = "carrinho.produto.idProduto")
	CarrinhoDeComprasDto toCarrinhoDeCompraDto(Carrinho carrinho);
	
	@Mapping(target = "idCliente", source = "carrinho.cliente.idCliente")
	@Mapping(target = "idProduto", source = "carrinho.produto.idProduto")
	@Mapping(target = "nomeProduto", source = "carrinho.produto.nomeProduto")
	@Mapping(target = "preco", source = "carrinho.produto.preco")
	@Mapping(target = "imagem", source = "carrinho.produto.imagem")
	ProdutosCarrinhoDto toProdutosCarrinhoDto(Carrinho carrinho);
}
