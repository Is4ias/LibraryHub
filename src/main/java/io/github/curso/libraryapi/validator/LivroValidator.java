package io.github.curso.libraryapi.validator;

import io.github.curso.libraryapi.exceptions.CampoInvalidoException;
import io.github.curso.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.curso.libraryapi.model.Livro;
import io.github.curso.libraryapi.repository.AutorRepository;
import io.github.curso.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private final static int ANO_EXIGENCIA_PRECO = 2020;
    private final LivroRepository repository;


    public void validar(Livro livro){
        if ( existeLivroComISBN(livro)){
            throw new RegistroDuplicadoException("ISBN já cadastrado!");
        }

        if (isPrecoObrigatorioNull(livro)) {
            throw new CampoInvalidoException("preco", "Para livros com o ano de publicação a partir de 2020 o ano é obrigatório!");
        }
    }

    private boolean isPrecoObrigatorioNull(Livro livro) {
        return livro.getPreco() == null &&
                livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO;
    }

    private boolean existeLivroComISBN(Livro livro){
        Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());

        if(livro.getId() == null ) {
            return livroEncontrado.isPresent();
        }

        return livroEncontrado
                .map(Livro::getId)
                .stream()
                .anyMatch(id -> !id.equals(livro.getId()));
    }
}
