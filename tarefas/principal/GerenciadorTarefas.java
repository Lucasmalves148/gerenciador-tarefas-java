package tarefas.principal;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

import tarefas.enums.Categoria;
import tarefas.enums.Prioridade;
import tarefas.enums.Status;
import tarefas.exceptions.IdNaoEncontradoException;
import tarefas.exceptions.TituloInvalidoException;

public class GerenciadorTarefas {

    private Set<Tarefa> tarefas = new HashSet<>();

    public GerenciadorTarefas() {
        tarefas = new HashSet<>();
    }

    public Set<Tarefa> imprimirTodasTarefas() {
        return tarefas;
    }

    public Map<Status, Set<Tarefa>> imprimirPorStatus() {
        return tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getStatus, Collectors.toSet()));
    }

    public Set<Tarefa> adicionarTarefa(Set<Tarefa> tarefa) {
        for (Tarefa t : tarefa) {
            tarefas.add(t);
        }
        return tarefas;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public void removerTarefa(Long idTarefa) {
        tarefas.removeIf(t -> t.getId().equals(idTarefa));
    }

    public void atualizarNomeTarefa(Long idTarefa, String novoTitulo) {

        if (novoTitulo == null || novoTitulo.isBlank()) {
            throw new TituloInvalidoException("Não é permitido este título.");
        }

        if (novoTitulo.length() < 3) {
            throw new TituloInvalidoException("Não é possível um título com menos de 3 dígitos.");
        }

        Tarefa tarefa = tarefas.stream()
                .filter(t -> t.getId().equals(idTarefa))
                .findFirst()
                .orElseThrow(() -> new IdNaoEncontradoException("Não foi posível uma tarefa com este id"));

        tarefa.setTitulo(novoTitulo);

    }

    public Set<Tarefa> buscarPorId(Long id) {
        return tarefas.stream()
                .filter(t -> t.getId().equals(id))
                .collect(Collectors.toSet());
    }

    public Map<Categoria, Set<Tarefa>> agruparTarefasPorCategoria() {

        Map<Categoria, Set<Tarefa>> tarefaMap = tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getCategoria, Collectors.toSet()));

        return tarefaMap;
    }

    public int quantidadeTotalDeTarefas() {
        return tarefas.size();
    }

    public Map<Categoria, Long> quantidadeDeTarefasPorCategoria() {
        return tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getCategoria, Collectors.counting()));
    }

    public boolean validarTituloUnico(String titulo) {

        return tarefas.stream()
                .anyMatch(t -> t.getTitulo().equalsIgnoreCase(titulo));

    }

    public Set<Tarefa> listarPorStatus(Status status) {
        return tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toSet());
    }

    public Set<Tarefa> listarPorCategoria(Categoria categoria) {
        return tarefas.stream()
                .filter(t -> t.getCategoria() == categoria)
                .collect(Collectors.toSet());
    }

    public Set<Tarefa> listarPorPrioridade(Prioridade prioridade) {
        return tarefas.stream()
                .filter(t -> t.getPrioridade() == prioridade)
                .collect(Collectors.toSet());
    }

    public Set<Tarefa> listarAtrsados() {
        return tarefas.stream()
                .filter(t -> t.getDataVencimento().isBefore(LocalDate.now()))
                .collect(Collectors.toSet());
    }

    public void marcarConcluido(Long id) {
        tarefas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresent(t -> t.setStatus(Status.CONCLUIDO));
    }

    public void reabrirTarefa(Long id) {

        tarefas.stream()
                .filter(t -> t.getId().equals(id) && t.getStatus() != Status.CONCLUIDO)
                .findFirst()
                .ifPresent(t -> t.setStatus(Status.PENDENTE));
    }

    public Set<Tarefa> buscarPorTitulo(String titulo) {

        return tarefas.stream()
                .filter(t -> t.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public Set<Tarefa> buscarPorDescricao(String descricao) {
        return tarefas.stream()
                .filter(t -> t.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public Map<Status, Long> contarTodosStatus() {
        return tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getStatus, Collectors.counting()));
    }

    public Long tarefasAtrasadas() {
        return tarefas.stream()
                .filter(t -> t.getStatus() != Status.CONCLUIDO && t.getDataVencimento().isBefore(LocalDate.now()))
                .count();

    }

    public Double mediaTarefasPorCategoria() {
        // pedi ajuda pro deepseek na parte de return, ele que fez, o restante fui eu
        // mesmo.
        Map<Categoria, Long> contagem = tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getCategoria, Collectors.counting()));

        return contagem.values().stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0);
    }

    public Map<Categoria, Long> topTresCategorias() {

        //Não consegui fazer sozinho, nem com StackOverFlow, entao tive que usar IA.
        return tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getCategoria, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Categoria, Long>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Long relatoriosConcluidos() {
        return tarefas.stream()
                .filter(t -> t.getStatus() == Status.CONCLUIDO)
                .count();
    }

    public Long relatorioPendentes() {
        return tarefas.stream()
                .filter(t -> t.getStatus() == Status.PENDENTE)
                .count();
    }

    public Long relatoriosEmAndamento() {
        return tarefas.stream()
                .filter(t -> t.getStatus() == Status.EM_ANDAMENTO)
                .count();
    }

    public Set<Tarefa> tarefaMaisAntigaNaoConcluida() {
        return tarefas.stream()
                .filter(t -> t.getStatus() != Status.CONCLUIDO)
                .min(Comparator.comparing(Tarefa::getDataCriacao))
                .map(Set::of)
                .orElse(Set.of());
    }

    public Set<Tarefa> tarefaMaiorAtraso() {
        return tarefas.stream()
                .filter(t -> t.getStatus() != Status.CONCLUIDO)
                .min(Comparator.comparing(Tarefa::getDataVencimento))
                .map(Set::of)
                .orElse(Set.of());
    }

    public Map<Prioridade, Long> estatisticaPorPrioridade() {

        return tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getPrioridade, Collectors.counting()));
    }

    public Set<Tarefa> getTarefas() {
        return tarefas;
    }
}