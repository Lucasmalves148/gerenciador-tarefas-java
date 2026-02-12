package tarefas.Excptions;

public class TituloInvalidoException extends RuntimeException {

    public TituloInvalidoException() {

    }

    public TituloInvalidoException(String msg) {
        super(msg);
    }
}
