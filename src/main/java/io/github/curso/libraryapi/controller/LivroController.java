package io.github.curso.libraryapi.controller;

import io.github.curso.libraryapi.controller.dto.ErrorResposta;
import io.github.curso.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.curso.libraryapi.controller.dto.ResultadoLivroDTO;
import io.github.curso.libraryapi.controller.mappers.LivroMapper;
import io.github.curso.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.curso.libraryapi.model.GeneroLivro;
import io.github.curso.libraryapi.model.Livro;
import io.github.curso.libraryapi.repository.LivroRepository;
import io.github.curso.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GerarHeaderLocation{

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto){
            Livro livro = mapper.toEntity(dto);
            service.salvar(livro);

            var url = gerarHeaderLocation(livro.getId());
            return ResponseEntity.created(url).location(url).build();
    }
    @GetMapping("{id}")
    public ResponseEntity<ResultadoLivroDTO> obterDetalhes
            (@PathVariable("id") String id){
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet( () -> ResponseEntity.notFound().build() );
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleltar(@PathVariable("id") String id) {
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    service.deletar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ResultadoLivroDTO>> pesquisa(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "titulo", required = false)
            String titulo,
            @RequestParam(value = "nome-autor", required = false)
            String nomeAutor,
            @RequestParam(value = "genero", required = false)
            GeneroLivro genero,
            @RequestParam(value = "ano-publicacao", required = false)
            Integer anoPublicacao
    ) {
        var resultado = service.pesquisa(isbn, titulo, nomeAutor, genero, anoPublicacao);
        var lista = resultado
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
