package commands;

import java.util.List;
import java.util.Scanner;

import domain.Tarefa;
import repository.UsuarioRepository;
import service.UsuarioService;
import validators.IndexValidator;
import validators.NonEmptyValidator;
import validators.OptionalDateValidator;
import validators.OptionalPrioridadeValidator;
import validators.ValidationContext;

public class EditarTarefaCommand implements Command {

    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        
        String termo = strValidationContext.getValidValue("Digite parte do título ou descrição para buscar: ", "O valor não pode ser vazio.", String.class);
        
        List<Tarefa> listaDeTarefasBuscadas = usuarioService.buscarTarefa(termo);
        
        if (!listaDeTarefasBuscadas.isEmpty()) {
            int index = 0;
            for (Tarefa tarefa : usuarioService.listarTarefa()) {
                Object[] params = new String[] {tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getCategoria(), tarefa.getPrazoDeConclusao(), tarefa.getStatus()}; 
                System.out.printf("================ [TAREFA %d] ===============%n", ++index);
                System.out.printf("""
                        TÍTULO: %s\nDESCRIÇÃO: %s\nPRIORIDADE: %s\nCATEGORIA: %s\nPRAZO: %s\nSTATUS DE CONCLUSÃO: %s
                        """, params);
                System.out.printf("============================================\n");
            }

            ValidationContext<Integer> intValidationContext = new ValidationContext<>(new IndexValidator(1, index));
            int indexInformado = intValidationContext.getValidValue("\nDigite o indice da tarefa para remover: ", String.format("Digite um indice válido (1, %d)", index), Integer.class);
            
            Tarefa tarefaEdit = listaDeTarefasBuscadas.get(indexInformado - 1);

            System.out.print("Digite um novo título (ou deixe vazio para não mudar): ");
            String tituloEdit = new Scanner(System.in).nextLine();

            System.out.print("Digite uma nova descrição (ou deixe vazio para não mudar): ");
            String descricaoEdit = new Scanner(System.in).nextLine();

            strValidationContext.setValidator(new OptionalPrioridadeValidator());
            String prioridadeEdit = strValidationContext.getValidValue("Digite uma nova prioridade [1 - MÁXIMA], [2 - COMUM], [3 - MÍNIMA] (ou deixe vazio para não mudar): ", "Valor inválido. (1, 2 ou 3)", String.class);

            System.out.print("Digite uma nova categoria (ou deixe vazio para não mudar): ");
            String categoriaEdit = new Scanner(System.in).nextLine();

            strValidationContext.setValidator(new OptionalDateValidator());
            String prazoEdit = strValidationContext.getValidValue("Digite um novo prazo [dd/mm/aaaa] (ou deixe vazio para não mudar): ", "Data inválida. (dd/mm/aaaa)", String.class);

            if (!tituloEdit.equals("")) {
                tarefaEdit.setTitulo(tituloEdit);
            }

            if (!descricaoEdit.equals("")) {
                tarefaEdit.setDescricao(descricaoEdit);
            }

            if (!prioridadeEdit.equals("")) {
                switch (prioridadeEdit) {
                    case "1":
                        prazoEdit = "MÁXIMA";
                        break;
                    case "2":
                        prazoEdit = "COMUM";
                        break;
                    case "3":
                        prazoEdit = "MÍNIMA";
                        break;
                    default:
                        break;
                }
                tarefaEdit.setPrioridade(prioridadeEdit);
            }

            if (!categoriaEdit.equals("")) {
                tarefaEdit.setCategoria(categoriaEdit);
            }

            if (!prazoEdit.equals("")) {
                tarefaEdit.setPrazoDeConclusao(prazoEdit);
            }

            usuarioService.atualizarTarefa(tarefaEdit.getTitulo(), tarefaEdit.getDescricao(), tarefaEdit.getPrioridade(), tarefaEdit.getCategoria(), tarefaEdit.getPrazoDeConclusao());
            System.out.println("Tarefa editada com sucesso!");
        } else {
            System.out.println("Nenhuma tarefa encontrada.");
        }
    }
    
}
