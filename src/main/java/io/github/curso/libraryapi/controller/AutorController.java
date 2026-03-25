package io.github.curso.libraryapi.controller;

import io.github.curso.libraryapi.controller.dto.AutorDTO;
import io.github.curso.libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autores")
public class AutorController {

    public AutorService service;
    public AutorController(AutorService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody AutorDTO autor){
        return new ResponseEntity("Autor " + autor + " salvo com sucesso!", HttpStatus.CREATED);

    }


}
