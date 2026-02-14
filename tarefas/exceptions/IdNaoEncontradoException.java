package tarefas.exceptions;

public class IdNaoEncontradoException extends RuntimeException {

    public IdNaoEncontradoException() {

    }

    public IdNaoEncontradoException(String msg) {
        super(msg);
    }
}
