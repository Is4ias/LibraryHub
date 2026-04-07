package io.github.curso.libraryapi.validator;

import io.github.curso.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.curso.libraryapi.model.Autor;
import io.github.curso.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {


    private AutorRepository repository;

    public AutorValidator(AutorRepository repository){
        this.repository = repository;
    }

    public void validar(Autor autor){
        if(existeAutor(autor)){
            throw new RegistroDuplicadoException("Autor já cadastrado!");
        }


    }
    private boolean existeAutor(Autor autor){
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()
        );
        if (autor.getId() == null){
            return autorEncontrado.isPresent();
        }
        return autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }
}
