package io.github.curso.libraryapi.controller;

import io.github.curso.libraryapi.controller.dto.ErrorResposta;
import io.github.curso.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.curso.libraryapi.controller.mappers.LivroMapper;
import io.github.curso.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.curso.libraryapi.model.Livro;
import io.github.curso.libraryapi.repository.LivroRepository;
import io.github.curso.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GerarHeaderLocation{

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        try{
            Livro livro = mapper.toEntity(dto);
            service.salvar(livro);

            var url = gerarHeaderLocation(livro.getId());
            return ResponseEntity.created(url).location(url).build();

        } catch (RegistroDuplicadoException e){
            var erroDTO = ErrorResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
