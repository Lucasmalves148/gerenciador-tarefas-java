package tarefas.enums;

public enum Categoria {
    TRABALHO("Trabalho"),
    ESTUDO("Estudo"),
    PESSOAL("Pessoal"),
    SAUDE("Saúde"),
    OUTROS("Outros");

    private String titulo;

    Categoria(String titulo) {
        this.titulo = titulo;
    }

    public static Categoria categoriaString(String titulo) {
        for (Categoria c : Categoria.values()) {
            if (c.titulo.equalsIgnoreCase(titulo)) {
                return c;
            }
        }
        return null;
    }
    

}
