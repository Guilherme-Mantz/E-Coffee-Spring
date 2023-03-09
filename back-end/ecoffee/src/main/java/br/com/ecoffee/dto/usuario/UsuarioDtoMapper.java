package br.com.ecoffee.dto.usuario;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.ecoffee.model.usuario.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioDtoMapper {

	UsuarioDtoMapper INSTANCE = Mappers.getMapper(UsuarioDtoMapper.class);

	UsuarioDto toUsuarioDto(Usuario usuario);
}
