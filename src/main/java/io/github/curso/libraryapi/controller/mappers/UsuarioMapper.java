package io.github.curso.libraryapi.controller.mappers;

import io.github.curso.libraryapi.controller.dto.UsuarioDTO;
import io.github.curso.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}

