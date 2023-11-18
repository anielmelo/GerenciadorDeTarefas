package commands;

import repository.UsuarioRepository;
import service.UsuarioService;
import validators.DateValidator;
import validators.NonEmptyValidator;
import validators.PrioridadeValidator;
import validators.ValidationContext;

public class CriarTarefaCommand implements Command {

    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());

        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        
        String titulo = strValidationContext.getValidValue("Titulo: ", "O titulo não pode ser vazio.", String.class);
        String descricao = strValidationContext.getValidValue("Descrição: ", "Descrição não pode ser vazio.", String.class);
        
        strValidationContext.setValidator(new PrioridadeValidator());
        String prioridade = strValidationContext.getValidValue("Prioridade [1 - MÁXIMA], [2 - COMUM], [3 - MÍNIMA]: ", "Digite um valor válido. [1, 2 OU 3].", String.class);
        
        strValidationContext.setValidator(new NonEmptyValidator());
        String categoria = strValidationContext.getValidValue("Categoria: ", "Categoria não pode ser vazio.", String.class);
        
        strValidationContext.setValidator(new DateValidator());
        String prazo = strValidationContext.getValidValue("Prazo [dd/mm/aaaa]: ", "Digite uma data válida. (dd/mm/aaaa)", String.class);

        switch (prioridade) {
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

        usuarioService.criarTarefa(titulo, descricao, prioridade, categoria, prazo);
    }
    
}
