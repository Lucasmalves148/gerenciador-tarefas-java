package tarefas.Principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tarefas.Enums.Categoria;
import tarefas.Enums.Prioridade;

public class Main {

        public static void main(String[] args) {

                Tarefa t1 = new Tarefa("Estudar Java", "Finalizar projeto", LocalDate.now().plusDays(3),
                                Prioridade.ALTA, Categoria.ESTUDO);
                Tarefa t2 = new Tarefa("Ir ao mercado", "Comprar arroz", LocalDate.now().plusDays(1), Prioridade.MEDIA,
                                Categoria.PESSOAL);
                Tarefa t3 = new Tarefa("Pagar contas", "Água e luz", LocalDate.now().minusDays(5), Prioridade.ALTA,
                                Categoria.PESSOAL);
                Tarefa t4 = new Tarefa("Academia", "Treino A", LocalDate.now().minusDays(2), Prioridade.MEDIA,
                                Categoria.SAUDE);
                Tarefa t5 = new Tarefa("Reunião", "Apresentar", LocalDate.now().plusDays(2), Prioridade.ALTA,
                                Categoria.TRABALHO);
                Tarefa t6 = new Tarefa("Ler livro", "Capítulo 1", LocalDate.now().minusDays(10), Prioridade.BAIXA,
                                Categoria.ESTUDO);
                Tarefa t7 = new Tarefa("Consulta", "Dentista", LocalDate.now().plusDays(15), Prioridade.ALTA,
                                Categoria.SAUDE);
                Tarefa t8 = new Tarefa("Planilhas", "Relatório", LocalDate.now().minusDays(3), Prioridade.MEDIA,
                                Categoria.TRABALHO);
                Tarefa t9 = new Tarefa("Inglês", "Listening", LocalDate.now().plusDays(4), Prioridade.MEDIA,
                                Categoria.ESTUDO);
                Tarefa t10 = new Tarefa("Limpar casa", "Quarto", LocalDate.now().minusDays(1), Prioridade.BAIXA,
                                Categoria.PESSOAL);
                Tarefa t11 = new Tarefa("Correr", "5km", LocalDate.now().plusDays(2), Prioridade.MEDIA,
                                Categoria.SAUDE);
                Tarefa t12 = new Tarefa("Relatório", "Entregar", LocalDate.now().minusDays(7), Prioridade.ALTA,
                                Categoria.TRABALHO);
                Tarefa t13 = new Tarefa("Comprar presente", "Aniversário", LocalDate.now().minusDays(4),
                                Prioridade.MEDIA, Categoria.PESSOAL);
                Tarefa t14 = new Tarefa("Estudar Python", "Automação", LocalDate.now().plusDays(5), Prioridade.ALTA,
                                Categoria.ESTUDO);
                Tarefa t15 = new Tarefa("Farmácia", "Remédio", LocalDate.now().minusDays(2), Prioridade.ALTA,
                                Categoria.SAUDE);
                Tarefa t16 = new Tarefa("Revisão código", "Bug", LocalDate.now().plusDays(1), Prioridade.ALTA,
                                Categoria.TRABALHO);
                Tarefa t17 = new Tarefa("Meditar", "10min", LocalDate.now().plusDays(0), Prioridade.BAIXA,
                                Categoria.SAUDE);
                Tarefa t18 = new Tarefa("Organizar mesa", "Home office", LocalDate.now().minusDays(6), Prioridade.BAIXA,
                                Categoria.TRABALHO);
                Tarefa t19 = new Tarefa("Curso", "Spring Boot", LocalDate.now().plusDays(10), Prioridade.ALTA,
                                Categoria.ESTUDO);
                Tarefa t20 = new Tarefa("Ligar mãe", "Telefonema", LocalDate.now().minusDays(8), Prioridade.MEDIA,
                                Categoria.PESSOAL);

                List<Tarefa> tarefas = new ArrayList<>();
                tarefas.add(t1);
                tarefas.add(t2);
                tarefas.add(t3);
                tarefas.add(t4);
                tarefas.add(t5);
                tarefas.add(t6);
                tarefas.add(t7);
                tarefas.add(t8);
                tarefas.add(t9);
                tarefas.add(t10);
                tarefas.add(t11);
                tarefas.add(t12);
                tarefas.add(t13);
                tarefas.add(t14);
                tarefas.add(t15);
                tarefas.add(t16);
                tarefas.add(t17);
                tarefas.add(t18);
                tarefas.add(t19);
                tarefas.add(t20);

                GerenciadorTarefas gerenciadorTarefas = new GerenciadorTarefas();
                gerenciadorTarefas.adicionarTarefa(tarefas);
                gerenciadorTarefas.marcarConcluido(5L);
                gerenciadorTarefas.marcarConcluido(12L);
                gerenciadorTarefas.marcarConcluido(20L);

                gerenciadorTarefas.relatorioGeralDasTarefas();
        }
}
