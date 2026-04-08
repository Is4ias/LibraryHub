package io.github.curso.libraryapi.repository;

import io.github.curso.libraryapi.model.Autor;
import io.github.curso.libraryapi.model.GeneroLivro;
import io.github.curso.libraryapi.model.Livro;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 * */


public interface LivroRepository extends JpaRepository<Livro, UUID> {
    // QUERY METHODS

    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByGenero(GeneroLivro genero);

    List<Livro> findByIsbn(String isbn);

    @Query("select l from Livro as l order by l.titulo")
    List<Livro> listarTodos();

    @Query("select a from Livro l join l.autor a")
    List<Autor> ListarAutores();

    @Query("select distinct l.titulo from Livro l")
    List<String> ListarDiferentesLivros();

    @Query("""
            
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasilieira'
            order by l.genero
            """)
    List<String> ListarGeneroBR();

    // named parameters -> parametros nomeados
    @Query("select l from Livro l where l.genero = :genero")
    List<Livro> findByGeneroPositional(
            @Param("genero") GeneroLivro generoLivro);

    @Query("select l from Livro l where l.genero = ?1 order by ?2")
    List<Livro> findByPositionalParameters(GeneroLivro generoLivro, String nomePropiedade);

    @Modifying
    @Transactional
    @Query("delete from Livro where genero = ?1")
    void deletByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query("update Livro set dataPublicacao = ?1")
    void updateDataPubli(LocalDate novaData);

    boolean existsByAutor(Autor autor);
}
