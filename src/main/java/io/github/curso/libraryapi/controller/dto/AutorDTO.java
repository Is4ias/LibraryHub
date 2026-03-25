package io.github.curso.libraryapi.controller.dto;

import io.github.curso.libraryapi.model.Autor;
import io.github.curso.libraryapi.service.AutorService;

import java.time.LocalDate;

public record AutorDTO(
        String nome,
        LocalDate dataNascimento,
        String nacionalidade) {

    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setNacionalidade(this.nacionalidade);
        autor.setDataNascimento(this.dataNascimento);
        return autor;

    }

}
