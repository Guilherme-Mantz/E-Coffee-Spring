package br.com.ecoffee.dto.cliente;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.ecoffee.model.cliente.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteDtoMapper {

	ClienteDtoMapper INSTANCE = Mappers.getMapper(ClienteDtoMapper.class);

	ClienteDto toClienteDto(Cliente cliente);

}
