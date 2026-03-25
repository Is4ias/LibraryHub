package io.github.curso.libraryapi.service;

import io.github.curso.libraryapi.model.Autor;
import io.github.curso.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);

    }

}
