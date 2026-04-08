package io.github.curso.libraryapi.exceptions;

public class OperacaoNaoPermitida extends  RuntimeException {
    public OperacaoNaoPermitida(String message) {
        super(message);
    }
}
