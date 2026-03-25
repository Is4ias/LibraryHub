package io.github.curso.libraryapi.service;

import io.github.curso.libraryapi.model.Autor;
import io.github.curso.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {
    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);

    }

    public Optional<Autor> getId(UUID id){
        return repository.findById(id);
    }
    public void deletar(Autor autor){
         repository.delete(autor);
    }

}
