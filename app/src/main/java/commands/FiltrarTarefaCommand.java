package commands;

import java.util.List;

import domain.Tarefa;
import repository.UsuarioRepository;
import service.UsuarioService;
import validators.PrioridadeValidator;
import validators.ValidationContext;

public class FiltrarTarefaCommand implements Command {

    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
        ValidationContext<String> strValidationContext = new ValidationContext<>(new PrioridadeValidator());
        
        String prioridade = strValidationContext.getValidValue("Digite a prioridade para buscar [1 - MÁXIMA], [2 - COMUM], [3 - MÍNIMA]: ", "Digite uma prioridade válida [1, 2 ou 3].", String.class);
        
        switch (prioridade) { // DEPOIS REVER O VALIDADOR DE PRIORIDADE
            case "1":
                prioridade = "MÁXIMA";
                break;
            case "2":
                prioridade = "COMUM";
                break;
            case "3":
                prioridade = "MÍNIMA";
                break;
            default:
                break;
        }

        List<Tarefa> listaDeTarefasBuscadas = usuarioService.filtrarTarefa(prioridade);
        
        if (!listaDeTarefasBuscadas.isEmpty()) {
            int index = 0;
            for (Tarefa tarefa : listaDeTarefasBuscadas) {
                System.out.printf("================ [TAREFA %d] ===============%n", ++index);
                System.out.println(tarefa.toString());
                System.out.printf("============================================\n");
            }
        } else {
            System.out.println("Nenhuma tarefa encontrada com esta prioridade.");
        }
    }
    
}
