package io.github.curso.libraryapi.controller;

import io.github.curso.libraryapi.controller.dto.ErrorResposta;
import io.github.curso.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.curso.libraryapi.repository.LivroRepository;
import io.github.curso.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;

    @PostMapping
    public ResponseEntity<Object> salvar(RequestBody @Valid CadastroLivroDTO dto){
        try{
            return ResponseEntity.ok(dto);
        } catch (RegistroDuplicadoException e){
            var erroDTO = ErrorResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
