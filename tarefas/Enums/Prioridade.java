package tarefas.Enums;

public enum Prioridade {
    BAIXA(3),
    MEDIA(2),
    ALTA(1);

    
    private int nivelPrioridade;

    Prioridade(int nivelPrioridade){
        this.nivelPrioridade = nivelPrioridade;
    }  
    

    public int getNivelPrioridade() {
        return nivelPrioridade;
    }
}
