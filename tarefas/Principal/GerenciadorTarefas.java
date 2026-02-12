package tarefas.Principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import tarefas.Enums.Categoria;
import tarefas.Enums.Prioridade;
import tarefas.Enums.Status;
import tarefas.Excptions.TituloInvalidoException;

public class GerenciadorTarefas {

    private List<Tarefa> tarefas = new ArrayList<>();

    public GerenciadorTarefas() {
        tarefas = new ArrayList<>();
    }

    public List<Tarefa> imprimirTodasTarefas() {
        return tarefas;
    }

    public Map<Status, List<Tarefa>> imprimirPorStatus() {
        return tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getStatus, Collectors.toList()));
    }

    public List<Tarefa> adicionarTarefa(List<Tarefa> tarefa) {
        for (Tarefa t : tarefa) {
            tarefas.add(t);
        }
        return tarefas;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public void removerTarefa(Long idTarefa) {
        for (Tarefa t : tarefas) {
            if (t.getId().equals(idTarefa)) {
                tarefas.remove(t);
                break;
            }
        }
    }

    public void atualizarNomeTarefa(Long idTarefa, String novoTitulo) {

        if (novoTitulo == null || novoTitulo.isBlank()) {
            throw new TituloInvalidoException("Não é permitido este título.");
        }

        if (novoTitulo.length() < 3) {
            throw new TituloInvalidoException("Não é possível um título com menos de 3 dígitos.");
        }

        boolean tituloJaExiste = tarefas.stream()

                .anyMatch(t -> !t.getId().equals(idTarefa) && t.getTitulo().equalsIgnoreCase(novoTitulo));

        if (tituloJaExiste) {
            throw new TituloInvalidoException(
                    "Não é possível atualizar para este nomes, pois ele já é uma tarefa existente.");
        }

        tarefas.stream()
                .filter(t -> t.getId().equals(idTarefa))
                .findFirst()
                .ifPresent(t -> t.setTitulo(novoTitulo));

    }

    public List<Tarefa> buscarPorId(Long id) {
        return tarefas.stream()
                .filter(t -> t.getId().equals(id))
                .toList();
    }

    public Map<Categoria, List<Tarefa>> agruparTarefasPorCategoria() {

        Map<Categoria, List<Tarefa>> tarefaMap = tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getCategoria));

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
        for (Tarefa t : tarefas) {
            if (t.getTitulo().equalsIgnoreCase(titulo)) {
                return true;
            }
        }
        return false;
    }

    public List<Tarefa> listarPorStatus(Status status) {
        return tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Tarefa> listarPorCategoria(Categoria categoria) {
        return tarefas.stream()
                .filter(t -> t.getCategoria() == categoria)
                .collect(Collectors.toList());
    }

    public List<Tarefa> listarPorPrioridade(Prioridade prioridade) {
        return tarefas.stream()
                .filter(t -> t.getPrioridade() == prioridade)
                .collect(Collectors.toList());
    }

    public List<Tarefa> listarAtrsados() {
        return tarefas.stream()
                .filter(t -> t.getDataVencimento().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
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

    public List<Tarefa> buscarPorTitulo(String titulo) {

        return tarefas.stream()
                .filter(t -> t.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Tarefa> buscarPorDescricao(String descricao) {
        return tarefas.stream()
                .filter(t -> t.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void relatorioGeralDasTarefas() {
        System.out.println("=== RELATÓRIO GERAL ===\n");

        System.out.println("Total de tarefas: " + quantidadeTotalDeTarefas());

        System.out.println("Pendentes: " + relatorioPendentes().size() +
                " (" + String.format("%.1f", (relatorioPendentes().size() * 100.0 / relatorioPendentes().size()))
                + "%)");

        System.out.println("Em andamento: " + relatoriosEmAndamento().size() +
                " (" + String.format("%.1f", (relatoriosEmAndamento().size() * 100.0 / relatoriosEmAndamento().size()))
                + "%)");

        System.out.println("Concluídas: " + relatoriosConcluidos() +
                " (" + String.format("%.1f", (relatoriosConcluidos() * 100.0 / relatoriosConcluidos()))
                + "%)");

        System.out.println("Atrasadas: " + tarefasAtrasadas());
        System.out.println("Média por categoria: " + String.format("%.2f", mediaTarefasPorCategoria()));
        System.out.println("Top 3 categorias: " + topTresCategorias());
        System.out.println("Tarefa mais antiga não concluída: " + tarefaMaisAntigaNaoConcluida());
        System.out.println("Tarefa com maior atraso: " + tarefaMaiorAtraso());
        System.out.println("Estatísticas por prioridade: " + estatisticaPorPrioridade());
        System.out.println("Quantidade de tipos de status: " + contarTodosStatus());
    }

    private Map<Status, Long> contarTodosStatus() {
        return tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getStatus, Collectors.counting()));
    }

    public List<Tarefa> tarefasAtrasadas() {
        return tarefas.stream()
                .filter(t -> t.getStatus() != Status.CONCLUIDO && t.getDataVencimento().isBefore(LocalDate.now()))
                .toList();

    }

    private Double mediaTarefasPorCategoria() {
        // pedi ajuda pro deepseek na parte de return, ele que fez, o restante fui eu
        // mesmo.
        Map<Categoria, Long> contagem = tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getCategoria, Collectors.counting()));

        return contagem.values().stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0);
    }

    public List<Categoria> topTresCategorias() {

        // Confesso, CHOREI PRO DEEPSEEK :(
        return tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getCategoria, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Categoria, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        /*
         * return quantidadeDeTarefasPorCategoria().values().stream()
         * .limit(3).toList();
         */
    }

    public Long relatoriosConcluidos() {
        return tarefas.stream()
                .filter(t -> t.getStatus() == Status.CONCLUIDO)
                .count();
    }

    public List<Tarefa> relatorioPendentes() {
        return tarefas.stream()
                .filter(t -> t.getStatus() == Status.PENDENTE)
                .toList();
    }

    public List<Tarefa> relatoriosEmAndamento() {
        return tarefas.stream()
                .filter(t -> t.getStatus() == Status.EM_ANDAMENTO)
                .toList();
    }

    public List<Tarefa> tarefaMaisAntigaNaoConcluida() {
        return tarefas.stream()
                .filter(t -> t.getStatus() != Status.CONCLUIDO)
                .min(Comparator.comparing(Tarefa::getDataCriacao))
                .map(List::of)
                .orElse(List.of());
    }

    public List<Tarefa> tarefaMaiorAtraso() {
        return tarefas.stream()
                .filter(t -> t.getStatus() != Status.CONCLUIDO)
                .min(Comparator.comparing(Tarefa::getDataVencimento))
                .map(List::of)
                .orElse(List.of());
    }

    public Map<Prioridade, Long> estatisticaPorPrioridade() {

        return tarefas.stream()
                .collect(Collectors.groupingBy(Tarefa::getPrioridade, Collectors.counting()));

    }
}