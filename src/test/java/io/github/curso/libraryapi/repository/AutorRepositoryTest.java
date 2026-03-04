package io.github.curso.libraryapi.repository;

import io.github.curso.libraryapi.model.Autor;
import io.github.curso.libraryapi.model.GeneroLivro;
import io.github.curso.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    void salvarAutor() {
        Autor autor = new Autor();
        autor.setNome("Monteiro Lobato");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        Autor salvo = repository.save(autor);
        System.out.println("Autor salvo: " + salvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("ba44a8f0-c1f5-4822-835c-2e06c981a7f4");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1980, 1, 20));

            repository.save(autorEncontrado);
        }

    }
    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);

    }
    @Test
    public void countTest(){
        System.out.println("Contagem de autores:" + repository.count());
    }

    @Test
    public void deleteId(){
        var id = UUID.fromString("ba44a8f0-c1f5-4822-835c-2e06c981a7f4");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("a8c06898-94e3-4987-b396-dcf57e16e38b");
        var machadoDeAssis = repository.findById(id).get();

        repository.delete(machadoDeAssis);

    }
    @Test
    void salvarAutorLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Jeff Kinney");
        autor.setNacionalidade("norte-americano");
        autor.setDataNascimento(LocalDate.of(1971, 2, 19));

        Livro livro = new Livro();
        livro.setIsbn("1234-9876");
        livro.setPreco(BigDecimal.valueOf(40));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Diario de um banana 1");
        livro.setDataPublicacao(LocalDate.of(2007, 1, 1));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("1277-9876");
        livro2.setPreco(BigDecimal.valueOf(40));
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setTitulo("Diario de um banana 2");
        livro2.setDataPublicacao(LocalDate.of(2008, 1, 1));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }
    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("9d0201b7-6e77-4f6d-bfea-373142e58cb4");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);
        autor.getLivros().forEach(System.out::println);

    }


}
