package io.github.curso.libraryapi.repository;

import io.github.curso.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

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
}
