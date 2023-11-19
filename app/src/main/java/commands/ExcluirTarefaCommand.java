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
                Object[] params = new String[] {tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getCategoria(), tarefa.getPrazoDeConclusao(), tarefa.getStatus()}; 
                System.out.printf("================ [TAREFA %d] ===============%n", ++index);
                System.out.printf("""
                        TÍTULO: %s\nDESCRIÇÃO: %s\nPRIORIDADE: %s\nCATEGORIA: %s\nPRAZO: %s\nSTATUS DE CONCLUSÃO: %s
                        """, params);
                System.out.printf("============================================%n");
                System.out.println();
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
