package tarefas.exceptions;

public class TituloInvalidoException extends RuntimeException {

    public TituloInvalidoException() {

    }

    public TituloInvalidoException(String msg) {
        super(msg);
    }
}
