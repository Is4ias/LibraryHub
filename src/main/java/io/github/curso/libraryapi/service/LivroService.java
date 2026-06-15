package io.github.curso.libraryapi.service;

import io.github.curso.libraryapi.model.GeneroLivro;
import io.github.curso.libraryapi.model.Livro;
import io.github.curso.libraryapi.repository.LivroRepository;
import io.github.curso.libraryapi.repository.LivroSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import  static io.github.curso.libraryapi.repository.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }
    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }
    public void deletar(Livro livro){
        repository.delete(livro);
    }
    public List<Livro> pesquisa(
            String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao){

        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction() );

        if (isbn != null) {
            specs = specs.and(isbnEquals(isbn));
        }
        if (titulo != null){
            specs = specs.and(tituloLike(titulo));
        }
        if(genero != null){
            specs = specs.and(generoEqual(genero));
        }
        if(anoPublicacao != null){
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }
        if( nomeAutor != null){
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        return repository.findAll(specs);

    }

    public void atualizar(Livro livro) {
        if(livro.getId() == null ) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o livro já esteja salvo no Banco de Dados");
        }
        repository.save(livro);
    }
}
