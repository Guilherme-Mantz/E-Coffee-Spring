package br.com.ecoffee.dto.endereco;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.ecoffee.model.endereco.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoDtoMapper {

	EnderecoDtoMapper INSTANCE = Mappers.getMapper(EnderecoDtoMapper.class);
	
	EnderecoDto toEnderecoDto(Endereco endereco);
}
