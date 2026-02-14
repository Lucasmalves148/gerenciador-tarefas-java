package tarefas.principal;

public class Main {
        public static void main(String[] args) {

                GerenciadorTarefas gerenciadorTarefas = new GerenciadorTarefas();
                RelatorioTarefas relatorioTarefas = new RelatorioTarefas(gerenciadorTarefas);
                relatorioTarefas.menuRelatorio();
        }
}