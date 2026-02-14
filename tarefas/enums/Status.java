package tarefas.enums;

public enum Status {
    PENDENTE(3),
    EM_ANDAMENTO(2),
    CONCLUIDO(1);

    private final Integer valorStatus;

    Status(Integer valorStatus) {
        this.valorStatus = valorStatus;
    }


    public Integer getValorStatus() {
        return valorStatus;
    }
    

}
