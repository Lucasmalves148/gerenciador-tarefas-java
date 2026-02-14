package tarefas.principal;

import java.time.LocalDate;

import tarefas.enums.Categoria;
import tarefas.enums.Prioridade;
import tarefas.enums.Status;

public class Tarefa {

    private static Long contador = 1L;

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private Prioridade prioridade;
    private Categoria categoria;
    private Status status;

    public Tarefa(String titulo, String descricao, LocalDate dataVencimento, Prioridade prioridade,
            Categoria categoria) {
        this.id = contador++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = LocalDate.now();
        this.dataVencimento = LocalDate.parse(dataVencimento.toString());
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = Status.PENDENTE;
    }

    public Tarefa(String titulo, LocalDate dataVencimento, Prioridade prioridade,
            Categoria categoria) {
        this.id = contador++;
        this.titulo = titulo;
        this.dataCriacao = LocalDate.now();
        this.dataVencimento = LocalDate.parse(dataVencimento.toString());
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = Status.PENDENTE;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tarefa tarefa = (Tarefa) o;
        return titulo.equalsIgnoreCase(tarefa.titulo);
    }

    @Override
    public int hashCode() {
        return titulo.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "[id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", dataCriacao=" + dataCriacao
                + ", dataVencimento=" + dataVencimento + ", prioridade=" + prioridade + ", categoria=" + categoria
                + ", status=" + status + "]" + "\n";
    }

}