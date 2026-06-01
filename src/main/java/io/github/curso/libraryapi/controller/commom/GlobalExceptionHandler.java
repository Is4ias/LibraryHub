package io.github.curso.libraryapi.controller.commom;

import io.github.curso.libraryapi.controller.dto.ErroCampo;
import io.github.curso.libraryapi.controller.dto.ErrorResposta;
import io.github.curso.libraryapi.exceptions.OperacaoNaoPermitida;
import io.github.curso.libraryapi.exceptions.RegistroDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrors
                .stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErrorResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Error de validação!",
                 listaErros);
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResposta handlerDuplicadoException(RegistroDuplicadoException e){
        return ErrorResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(OperacaoNaoPermitida.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResposta handlerOpercaoNaoPermitidaException(
            OperacaoNaoPermitida e ) {
        return ErrorResposta.resostaPadrao(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResposta handlerErrosNaoTratados(RuntimeException e){
        return new ErrorResposta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro inesperado. Entre em contato  com a administração!", List.of()
        );
    }
}
