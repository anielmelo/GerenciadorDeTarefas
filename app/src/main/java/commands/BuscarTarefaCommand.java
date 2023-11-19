package commands;

import java.util.List;

import domain.Tarefa;
import repository.UsuarioRepository;
import service.UsuarioService;
import validators.NonEmptyValidator;
import validators.ValidationContext;

public class BuscarTarefaCommand implements Command {

    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        
        String termo = strValidationContext.getValidValue("Digite parte do título ou descrição para buscar: ", "O valor não pode ser vazio.", String.class);
        
        List<Tarefa> listaDeTarefasBuscadas = usuarioService.buscarTarefa(termo);
        
        if (!listaDeTarefasBuscadas.isEmpty()) {
            int index = 0;
            for (Tarefa tarefa : listaDeTarefasBuscadas) {
                Object[] params = new String[] {tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getCategoria(), tarefa.getPrazoDeConclusao(), tarefa.getStatus()}; 
                System.out.printf("================ [TAREFA %d] ===============%n", ++index);
                System.out.printf("""
                        TÍTULO: %s\nDESCRIÇÃO: %s\nPRIORIDADE: %s\nCATEGORIA: %s\nPRAZO: %s\nSTATUS DE CONCLUSÃO: %s
                        """, params);
                System.out.printf("============================================\n");
            }
        } else {
            System.out.println("Nenhuma tarefa encontrada.");
        }
    }
}
