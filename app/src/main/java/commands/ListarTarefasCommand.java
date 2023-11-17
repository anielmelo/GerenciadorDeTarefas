package commands;

import java.util.List;

import domain.Tarefa;
import repository.UsuarioRepository;
import service.UsuarioService;
import view.ConsoleColors;

public class ListarTarefasCommand implements Command {
    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
        List<Tarefa> listaDeTarefa = usuarioService.getUsuarioAtual().getListaDeTarefa();

        for (Tarefa tarefa : listaDeTarefa) {
            Object[] params = new String[] {tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getCategoria(), tarefa.getPrazoDeConclusao(), tarefa.getStatus()}; 
            System.out.printf("%s================ %s[TAREFA %d]%s ===============%s%n", ConsoleColors.BLACK_BOLD, ConsoleColors.CYAN_BOLD, (listaDeTarefa.indexOf(tarefa)  + 1), ConsoleColors.BLACK_BOLD, ConsoleColors.RESET);
            System.out.printf("""
                    TÍTULO: %s\nDESCRIÇÃO: %s\nPRIORIDADE: %s\nCATEGORIA: %s\nPRAZO %s\nSTATUS DE CONCLUSÃO: %s\n
            """, params);
            System.out.printf("%s============================================%s%n", ConsoleColors.BLACK_BOLD, ConsoleColors.RESET);
            System.out.println();
        }
    }
}