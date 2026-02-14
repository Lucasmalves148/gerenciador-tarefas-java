package tarefas.enums;

public enum Prioridade {
    BAIXA("Baixa", 3),
    MEDIA("Média", 2),
    ALTA("Alta", 1);

    private String titulo;
    private int nivelPrioridade;

    Prioridade(String titulo, int nivelPrioridade) {
        this.titulo = titulo;
        this.nivelPrioridade = nivelPrioridade;
    }

    public static Prioridade prioridadeString(String titulo) {
        for (Prioridade p : Prioridade.values()) {
            if (p.titulo.equalsIgnoreCase(titulo)) {
                return p;
            }
        }
        return null;
    }

    public int getNivelPrioridade() {
        return nivelPrioridade;
    }
}
