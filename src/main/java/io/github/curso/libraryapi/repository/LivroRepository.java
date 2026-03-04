package io.github.curso.libraryapi.repository;

import io.github.curso.libraryapi.model.Autor;
import io.github.curso.libraryapi.model.GeneroLivro;
import io.github.curso.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    // QUERY METHODS

    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByGenero(GeneroLivro genero);

    List<Livro> findByIsbn(String isbn);
}
