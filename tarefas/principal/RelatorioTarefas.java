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

    private void menuInicial() {
        System.out.println("MENU PRINCIPAL");
        System.out.println("1 - Modificar uma tarefa");
        System.out.println("2 - Visualizar estatísticas");
        System.out.println("3 - Sair");
        System.out.println();
    }

    private void mensagensTarefas() {
        System.out.println("MODIFICAÇÃO DE TAREFAS");
        System.out.println("1 - Criar uma tarefa");
        System.out.println("2 - Deletar uma tarefa");
        System.out.println("3 - Atualizar o nome");
        System.out.println("4 - Exibir todas as tarefas");
        System.out.println("5 - Voltar");
        System.out.println();
    }

    private void mensagensRelatorio() {
        System.out.println("RELATÓRIO DE TAREFAS\n");
        System.out.println("1  - Total de tarefas");
        System.out.println("2  - Pendentes");
        System.out.println("3  - Em andamento");
        System.out.println("4  - Concluídas");
        System.out.println("5  - Atrasadas");
        System.out.println("6  - Tarefa mais atrasada");
        System.out.println("7  - Tarefa mais antiga não concluída");
        System.out.println("8  - Top 3 categorias");
        System.out.println("9  - Média por categoria");
        System.out.println("10 - Estatísticas por prioridade");
        System.out.println("11 - Quantidade por status");
        System.out.println("12 - Relatório geral");
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
        System.out.println("Tarefa com maior atraso: \n" + gerenciadorTarefas.tarefaMaiorAtraso());
        System.out.println("Estatísticas por prioridade: " + gerenciadorTarefas.estatisticaPorPrioridade());
        System.out.println("Quantidade de status: " + gerenciadorTarefas.contarTodosStatus());
    }

    public void menuRelatorio() {

        Scanner tec = new Scanner(System.in);
        int opc = 0;
        do {
            try {
                menuInicial();

                opc = tec.nextInt();
                tec.nextLine();
                switch (opc) {
                    case 1:
                        mensagensTarefas();
                        int opcTarefas = tec.nextInt();
                        tec.nextLine();
                        if (opcTarefas == 1) {
                            System.out.println("Digite abaixo na seguinte ordem:");
                            System.out.print("Título: ");
                            String titulo = tec.nextLine();

                            System.out.print("Descrição: ");
                            String descricao = tec.nextLine();

                            System.out.print("Data de vencimento (dd-MM-yyyy): ");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate dataVencimento = LocalDate.parse(tec.nextLine(), formatter);

                            System.out.print("Prioridade (BAIXA, MEDIA, ALTA): ");
                            Prioridade prioridade = Prioridade.prioridadeString(tec.nextLine());

                            System.out.print("Categoria (TRABALHO, ESTUDOS, PESSOAL, OUTROS): ");
                            Categoria categoria = Categoria.categoriaString(tec.nextLine());
                            Tarefa tarefa = new Tarefa(titulo, descricao, dataVencimento, prioridade, categoria);
                            if(tarefa != null) System.out.println("Tarefa criada com sucesso!!");
                            System.out.println("\n");
                            gerenciadorTarefas.adicionarTarefa(tarefa);
                            
                        } else if (opcTarefas == 2) {
                            System.out.print("Digite o id da tarefa a ser removido: ");
                            Long idTarefa = tec.nextLong();
                            gerenciadorTarefas.removerTarefa(idTarefa);
                        } else if (opcTarefas == 3) {
                            System.out.print("Digite o id da tarefa que deseja renomear: ");
                            Long id = tec.nextLong();
                            System.out.print("Agora o novo nome: ");
                            String novoNome = tec.nextLine();
                            gerenciadorTarefas.atualizarNomeTarefa(id, novoNome);
                            tec.nextLine();

                        } else if (opcTarefas == 4) {
                            System.out.println(gerenciadorTarefas.imprimirTodasTarefas());
                        } else if (opcTarefas == 5) {
                            System.out.println("\n".repeat(15));
                        }

                        break;
                    case 2:
                        mensagensRelatorio();
                        int opcRelatorio = tec.nextInt();
                        tec.nextLine();

                        if (opcRelatorio == 1) {
                            System.out.println("Total de tarefas: " + gerenciadorTarefas.quantidadeTotalDeTarefas());
                        } else if (opcRelatorio == 2) {
                            System.out.println("Tarefas pendentes: " + gerenciadorTarefas.relatorioPendentes());
                        } else if (opcRelatorio == 3) {
                            System.out.println("Tarefas em andamento: " + gerenciadorTarefas.relatoriosEmAndamento());
                        } else if (opcRelatorio == 4) {
                            System.out.println("Tarefas concluídas: " + gerenciadorTarefas.relatoriosConcluidos());
                        } else if (opcRelatorio == 5) {
                            System.out.println("Tarefas atrasadas: " + gerenciadorTarefas.tarefasAtrasadas());
                        } else if (opcRelatorio == 6) {
                            System.out.println("Tarefa mais atrasada: " + gerenciadorTarefas.tarefaMaiorAtraso());
                        } else if (opcRelatorio == 7) {
                            System.out.println("Tarefa mais antiga não concluída: "
                                    + gerenciadorTarefas.tarefaMaisAntigaNaoConcluida());
                        } else if (opcRelatorio == 8) {
                            System.out.println("Top 3 categorias: " + gerenciadorTarefas.topTresCategorias());
                        } else if (opcRelatorio == 9) {
                            System.out.println(
                                    "Média de tarefas por categoria: " + gerenciadorTarefas.mediaTarefasPorCategoria());
                        } else if (opcRelatorio == 10) {
                            System.out.println(
                                    "Estatísticas por prioridade: " + gerenciadorTarefas.estatisticaPorPrioridade());
                        } else if (opcRelatorio == 11) {
                            System.out.println("Quantidade por status: " + gerenciadorTarefas.contarTodosStatus());
                        } else if (opcRelatorio == 12) {
                            relatorioGeralDasTarefas();
                        } else if (opcRelatorio == 13) {
                            System.out.println("\n".repeat(15));
                        } else {
                            System.out.println("Opção inválida!");
                        }
                        break;
                    case 3:
                        System.out.println("Ok, saindo do sistema...");
                        tec.close();
                        break;
                    default:
                        System.out.println("Dígito inválido, tente novamente");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Erro, não é possível inserir um caractere não válido");
            } catch (DateTimeParseException ex) {
                System.out.println(
                        "Não é possível inserir esse valor como data, apenas padrão brasileiro, ou similares.");
            }
        } while (opc != 3);
    }
    

}