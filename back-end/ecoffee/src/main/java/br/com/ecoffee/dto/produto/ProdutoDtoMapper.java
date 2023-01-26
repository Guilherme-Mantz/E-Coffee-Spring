package br.com.ecoffee.dto.produto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.ecoffee.model.produto.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoDtoMapper {
	
	ProdutoDtoMapper INSTANCE = Mappers.getMapper(ProdutoDtoMapper.class);
	
	@Mapping(target = "marca", source = "produto.marca.marca")
	ProdutoDto toProdutoDto(Produto produto);
	
	@Mapping(target = "marca", source = "produto.marca.marca")
	DetalhesProdutoDto toDetalhesProdutoDto(Produto produto);

}
