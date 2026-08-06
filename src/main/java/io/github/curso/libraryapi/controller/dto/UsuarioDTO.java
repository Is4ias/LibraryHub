package io.github.curso.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(
        @NotBlank(message = "Campo Obrigatório")
        String login,
        @NotBlank(message = "Campo Obrigatório")
        String senha,
        @Email(message = "inválido")
        @NotBlank(message = "Campo Obrigatório")
        String email,
        List<String> roles) {
}
