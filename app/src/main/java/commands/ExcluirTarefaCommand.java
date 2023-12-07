package commands;

import java.util.List;

import domain.Tarefa;
import repository.UsuarioRepository;
import service.UsuarioService;
import validators.IndexValidator;
import validators.NonEmptyValidator;
import validators.ValidationContext;

public class ExcluirTarefaCommand implements Command {

    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        
        String termo = strValidationContext.getValidValue("Digite parte do título ou descrição para buscar: ", "O valor não pode ser vazio.", String.class);
        
        List<Tarefa> listaDeTarefasBuscadas = usuarioService.buscarTarefa(termo);
        
        if (!listaDeTarefasBuscadas.isEmpty()) {
            int index = 0;
            for (Tarefa tarefa : listaDeTarefasBuscadas) {
                System.out.printf("================ [TAREFA %d] ===============%n", ++index);
                System.out.println(tarefa.toString());
                System.out.printf("============================================\n");
            }

            ValidationContext<Integer> intValidationContext = new ValidationContext<>(new IndexValidator(1, index));
            int indexInformado = intValidationContext.getValidValue("\nDigite o indice da tarefa para remover: ", String.format("Digite um indice válido (1, %d)", index), Integer.class);
            
            usuarioService.removerTarefa(listaDeTarefasBuscadas.get(indexInformado - 1));
            System.out.printf("\nTarefa %d excluída.\n", indexInformado);
        } else {
            System.out.println("Nenhuma tarefa encontrada.");
        }
    }
    
}
