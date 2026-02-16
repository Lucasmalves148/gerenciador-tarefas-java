package tarefas.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import tarefas.enums.Categoria;
import tarefas.enums.Prioridade;

public class RelatorioTarefas {
    private GerenciadorTarefas gerenciadorTarefas;

    public RelatorioTarefas(GerenciadorTarefas gerenciadorTarefas) {
        this.gerenciadorTarefas = gerenciadorTarefas;
    }

    private void limparTela() {
        System.out.println("\n".repeat(10));
    }

    private void menuInicial() {
        limparTela();
        System.out.println("MENU PRINCIPAL");
        System.out.println("1 - Modificar uma tarefa");
        System.out.println("2 - Visualizar estatísticas");
        System.out.println("3 - Sair");
        System.out.println();
    }

    private void mensagensTarefas() {
        menuInicial();
        System.out.println("MODIFICAÇÃO DE TAREFAS");
        System.out.println("1 - Criar uma tarefa");
        System.out.println("2 - Deletar uma tarefa");
        System.out.println("3 - Atualizar o nome");
        System.out.println("4 - Exibir todas as tarefas");
        System.out.println("5 - Voltar");
        System.out.println();
    }

    private void mensagensRelatorio() {
        menuInicial();
        System.out.println("RELATÓRIO DE TAREFAS\n");
        System.out.println("1  - Total de tarefas");
        System.out.println("2  - Tarefas pendentes");
        System.out.println("3  - Tarefaas em andamento");
        System.out.println("4  - Tarefas concluídas");
        System.out.println("5  - Tarefas atrasadas");
        System.out.println("6  - Tarefa mais atrasada");
        System.out.println("7  - Tarefa mais antiga não concluída");
        System.out.println("8  - Top 3 categorias com mais tarefas");
        System.out.println("9  - Média de tarefas por categoria");
        System.out.println("10 - Estatísticas de tarefas por prioridade");
        System.out.println("11 - Quantidade de tarefas por status");
        System.out.println("12 - Relatório geral das tarefas");
        System.out.println("13 - Voltar");
        System.out.println();
    }

    private void relatorioGeralDasTarefas() {
        System.out.println("=== RELATÓRIO GERAL ===\n");

        System.out.println("Total de tarefas: " + gerenciadorTarefas.quantidadeTotalDeTarefas());

        Long pendentes = gerenciadorTarefas.relatorioPendentes();
        System.out.println("Pendentes: " + pendentes +
                " (" + String.format("%.1f", (pendentes * 100.0 / gerenciadorTarefas.getTarefas().size()))
                + "%)");

        Long emAndamento = gerenciadorTarefas.relatoriosEmAndamento();
        System.out.println("Em andamento: " + emAndamento +
                " (" + String.format("%.1f", (emAndamento * 100.0 / gerenciadorTarefas.getTarefas().size()))
                + "%)");

        Long concluidas = gerenciadorTarefas.relatoriosConcluidos();
        System.out.println("Concluídas: " + concluidas +
                " (" + String.format("%.1f", (concluidas * 100.0 / gerenciadorTarefas.getTarefas().size()))
                + "%)");

        Long tarefasAtrasadas = gerenciadorTarefas.tarefasAtrasadas();
        System.out.println("Atrasadas: " + tarefasAtrasadas);
        System.out.println(
                "Média por categoria: " + String.format("%.2f", gerenciadorTarefas.mediaTarefasPorCategoria()));
        System.out.println("Top 3 categorias com mais tarefas: " + gerenciadorTarefas.topTresCategorias());
        System.out.println("Tarefa mais antiga não concluída: \n" + gerenciadorTarefas.tarefaMaisAntigaNaoConcluida());
        System.out.println("Tar\n" + //
                "            tarefa.setTitulo(novoTitulo);efa com maior atraso: \n"
                + gerenciadorTarefas.tarefaMaiorAtraso());
        System.out.println("Estatísticas por prioridade: " + gerenciadorTarefas.estatisticaPorPrioridade());
        System.out.println("Quantidade de status: " + gerenciadorTarefas.contarTodosStatus());
    }

    private static Prioridade lerPrioridadeValida(Scanner tec) {
        Prioridade prioridade = null;
        while (prioridade == null) {
            System.out.print("Prioridade (BAIXA, MEDIA, ALTA): ");
            String entrada = tec.nextLine();
            prioridade = Prioridade.prioridadeString(entrada);

            if (prioridade == null) {
                System.out.println("Prioridade inválida! Digite BAIXA, MEDIA ou ALTA.");
            }
        }
        return prioridade;
    }

    private static Categoria lerCategoriaValida(Scanner tec) {
        Categoria categoria = null;
        while (categoria == null) {
            System.out.print("Categoria (TRABALHO, ESTUDOS, PESSOAL, OUTROS): ");
            String entrada = tec.nextLine();
            categoria = Categoria.categoriaString(entrada);

            if (categoria == null) {
                System.out.println("Categoria inválida! Digite TRABALHO, ESTUDOS, PESSOAL ou OUTROS.");
            }
        }
        return categoria;
    }

    private void gerenciadorDeEstatisticas(int opcRelatorio, Scanner tec) {

        switch (opcRelatorio) {
            case 1:
                System.out.println("Total de tarefas: " + gerenciadorTarefas.quantidadeTotalDeTarefas());
                menuInicial();
                break;
            case 2:
                System.out.println("Tarefas pendentes: " + gerenciadorTarefas.relatorioPendentes());
                menuInicial();
                break;
            case 3:
                System.out.println("Tarefas em andamento: " + gerenciadorTarefas.relatoriosEmAndamento());
                menuInicial();
                break;
            case 4:
                System.out.println("Tarefas concluídas: " + gerenciadorTarefas.relatoriosConcluidos());
                menuInicial();
                break;
            case 5:
                System.out.println("Tarefas atrasadas: " + gerenciadorTarefas.tarefasAtrasadas());
                menuInicial();
                break;
            case 6:
                System.out.println("Tarefa mais atrasada: " + gerenciadorTarefas.tarefaMaiorAtraso());
                menuInicial();
                break;
            case 7:
                System.out.println(
                        "Tarefa mais antiga não concluída: " + gerenciadorTarefas.tarefaMaisAntigaNaoConcluida());
                menuInicial();
                break;
            case 8:
                System.out.println("Top 3 categorias: " + gerenciadorTarefas.topTresCategorias());
                menuInicial();
                break;
            case 9:
                System.out.println("Média de tarefas por categoria: " + gerenciadorTarefas.mediaTarefasPorCategoria());
                break;
            case 10:
                System.out.println("Estatísticas por prioridade: " + gerenciadorTarefas.estatisticaPorPrioridade());
                menuInicial();
                break;
            case 11:
                System.out.println("Quantidade por status: " + gerenciadorTarefas.contarTodosStatus());
                menuInicial();
                break;
            case 12:
                relatorioGeralDasTarefas();
                menuInicial();
                break;
            case 13:
                System.out.println("\n".repeat(15));
            default:
                System.out.println("Opção inválida!");
                menuInicial();
        }
    }

    private void criarTarefa(Scanner tec) {
        System.out.println("Digite abaixo na seguinte ordem:");
        System.out.print("Título: ");
        String titulo = tec.nextLine();

        while (titulo.length() < 3) {
            System.out.println("Não é permitido um título com menos 3 dígitos. Insira um novo título");
            titulo = tec.nextLine();
        }
        System.out.print("Descrição: ");
        String descricao = tec.nextLine();

        LocalDate dataVencimento = null;

        while (dataVencimento == null) {
            try {
                System.out.print("Data de vencimento (dd/MM/yyyy): ");
                dataVencimento = LocalDate.parse(tec.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato dd/MM/yyyy");
            }
        }
        Prioridade prioridade = lerPrioridadeValida(tec);

        Categoria categoria = lerCategoriaValida(tec);

        Tarefa tarefa = new Tarefa(titulo, descricao, dataVencimento, prioridade, categoria);

        System.out.println("Tarefa criada com sucesso!!");
        System.out.println("\n");

        gerenciadorTarefas.adicionarTarefa(tarefa);
        menuInicial();
    }

    private void gerenciadorDeTarefas(int opc, Scanner tec) {
        switch (opc) {
            case 1:
                criarTarefa(tec);
                break;
            case 2:
                System.out.print("Digite o id da tarefa a ser removida: ");
                Long idTarefa = tec.nextLong();
                gerenciadorTarefas.removerTarefa(idTarefa);
                menuInicial();
                break;
            case 3:
                System.out.print("Digite o id da tarefa que deseja renomear: ");
                Long id = tec.nextLong();
                tec.nextLine();
                System.out.print("Agora o novo nome: ");
                String novoNome = tec.nextLine();
                gerenciadorTarefas.atualizarNomeTarefa(id, novoNome);
                menuInicial();
                break;
            case 4:
                System.out.println(gerenciadorTarefas.imprimirTodasTarefas());
                menuInicial();
                break;
            case 5:
                menuInicial();
                break;
            default:
                System.out.println("Opção fornecida inválida.");
                menuInicial();
                break;
        }
    }

    public void menuRelatorio() {

        Scanner tec = new Scanner(System.in);
        int opc = 0;
        menuInicial();
        do {
            try {

                opc = tec.nextInt();
                tec.nextLine();
                switch (opc) {
                    case 1:
                        mensagensTarefas();
                        int opcTarefas = tec.nextInt();
                        tec.nextLine();
                        gerenciadorDeTarefas(opcTarefas, tec);
                        break;
                    case 2:
                        mensagensRelatorio();
                        int opcRelatorio = tec.nextInt();
                        tec.nextLine();

                        gerenciadorDeEstatisticas(opcRelatorio, tec);
                        break;
                    case 3:
                        System.out.println("Ok, saindo do sistema...");
                        tec.close();
                        break;
                    default:
                        System.out.println("Dígito inválido, tente novamente");
                        menuInicial();
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Erro, não é possível inserir um caractere não válido");

                tec.nextLine();
                menuInicial();

            }
        } while (opc != 3);
    }

}