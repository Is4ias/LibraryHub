package io.github.curso.libraryapi.repository;

import io.github.curso.libraryapi.model.Autor;
import io.github.curso.libraryapi.model.GeneroLivro;
import io.github.curso.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("12345-54321");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = autorRepository.findById(UUID.fromString("1762fd40-c7ec-4f22-a755-4ea7ccbbf40c")).orElse(null);
        livro.setAutor(autor);

        repository.save(livro);
    }
    @Test
    void salvarAutorLivro(){
        Livro livro = new Livro();
        livro.setIsbn("1555-9876");
        livro.setPreco(BigDecimal.valueOf(300));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Teoria da relatividade");
        livro.setDataPublicacao(LocalDate.of(1960, 2, 20));

        Autor autor = new Autor();
        autor.setNome("Albert Einstein");
        autor.setNacionalidade("Alemão");
        autor.setDataNascimento(LocalDate.of(1879,3,14));

        autorRepository.save(autor);
        livro.setAutor(autor);
        repository.save(livro);
    }


    @Test
    void salvarTesteCascata(){
        Livro livro = new Livro();
        livro.setIsbn("09887-765876");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Amor e outras drogas");
        livro.setDataPublicacao(LocalDate.of(2000, 2, 20));

        Autor autor = new Autor();
        autor.setNome("Jessy");
        autor.setNacionalidade("Americana");
        autor.setDataNascimento(LocalDate.of(1976, 7,2));


        livro.setAutor(autor);

        repository.save(livro);
    }
    @Test
    void atualizarAutorLivro(){
        UUID id = UUID.fromString("5d8d972b-0063-4e7e-81b9-1c47a054e562");
        var livroAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("1762fd40-c7ec-4f22-a755-4ea7ccbbf40c");
        Autor JorgeAmado = autorRepository.findById(idAutor).orElse(null);

        livroAtualizar.setAutor(JorgeAmado);
        repository.save(livroAtualizar);

    }
    @Test
    void deletar(){
        UUID id = UUID.fromString("6f589e59-8f64-4a76-bb95-57663788dfce");
        repository.deleteById(id);
    }

    @Test
    void pesquisarTitulo(){
        List<Livro> lista = repository.findByTitulo("Diario de um banana 1");
        lista.forEach(System.out::println);
    }
    @Test
    void pesquiserIsbn(){
        List<Livro> lista = repository.findByIsbn("1234-9876");
        lista.forEach(System.out::println);
    }
    @Test
    void pesquiserGenero(){
        List<Livro> lista = repository.findByGenero(GeneroLivro.ROMANCE);
        lista.forEach(System.out::println);
    }
    @Test
    void ListarTodos(){
        var resultado = repository.listarTodos();
        resultado.forEach(System.out::println);
    }
    @Test
    void ListarAutores(){
        var resultado = repository.ListarAutores();
        resultado.forEach(System.out::println);
    }
    @Test
    void ListarDiferentesLivros(){
        var resultado = repository.ListarDiferentesLivros();
        resultado.forEach(System.out::println);
    }
    @Test
    void ListarGeneroBR(){
        var resultado = repository.ListarGeneroBR();
        resultado.forEach(System.out::println);
    }

    @Test
    void ListarPorGeneroParam(){
        var resultado = repository.findByGeneroPositional(GeneroLivro.FICCAO);
        resultado.forEach(System.out::println);

    }

    @Test
    void ListarGeneroPositonalTest(){
        var resultado = repository.findByPositionalParameters(GeneroLivro.ROMANCE, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void deleteLGenero(){
        repository.deletByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void updateDataPubli(){
        repository.updateDataPubli(LocalDate.of(2000, 1, 1));
    }


}