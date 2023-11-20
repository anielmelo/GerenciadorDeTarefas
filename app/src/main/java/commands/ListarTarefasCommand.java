package commands;

import domain.Tarefa;
import repository.UsuarioRepository;
import service.UsuarioService;

public class ListarTarefasCommand implements Command {
    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());

        int index = 0;
        if (!usuarioService.listarTarefa().isEmpty()) {
            for (Tarefa tarefa : usuarioService.listarTarefa()) {
                System.out.printf("================ [TAREFA %d] ===============%n", ++index);
                System.out.println(tarefa.toString());
                System.out.printf("============================================\n");
            }
        } else {
            System.out.println("A lista de tarefas está vazia.");
        }
    }
}
