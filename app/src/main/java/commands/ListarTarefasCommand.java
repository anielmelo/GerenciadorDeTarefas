package commands;

import domain.Tarefa;
import repository.UsuarioRepository;
import service.UsuarioService;

public class ListarTarefasCommand implements Command {
    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());

        int index = 0;
        for (Tarefa tarefa : usuarioService.listarTarefa()) {
            Object[] params = new String[] {tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getCategoria(), tarefa.getPrazoDeConclusao(), tarefa.getStatus()}; 
            System.out.printf("================ [TAREFA %d] ===============%n", ++index);
            System.out.printf("""
                    TÍTULO: %s\nDESCRIÇÃO: %s\nPRIORIDADE: %s\nCATEGORIA: %s\nPRAZO: %s\nSTATUS DE CONCLUSÃO: %s
                    """, params);
            System.out.printf("============================================%n");
            System.out.println();
        }
    }
}
