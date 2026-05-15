package io.github.curso.libraryapi.controller.mappers;


import io.github.curso.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.curso.libraryapi.model.Autor;
import io.github.curso.libraryapi.model.Livro;
import io.github.curso.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    protected AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(buscarAutor(dto.idAutor()) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    protected Autor buscarAutor(UUID idAutor){
        return autorRepository.findById(idAutor).orElse(null);
    }
}
