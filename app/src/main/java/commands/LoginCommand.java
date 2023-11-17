package commands;

import repository.UsuarioRepository;
import service.UsuarioService;
import validators.NameLoginValidator;
//import validators.NameValidator;
import validators.NonEmptyValidator;
import validators.ValidationContext;

public class LoginCommand implements Command {

    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
        System.out.println("=========================");
        ValidationContext<String> strValidaNome = new ValidationContext<>(new NonEmptyValidator());
        String nome = strValidaNome.getValidValue("Nome: ", "Digite o nome do usuário.", String.class);

        ValidationContext<String> strValidaSenha = new ValidationContext<>(new NonEmptyValidator());
        String senha = strValidaSenha.getValidValue("Senha: ", "Caixa de entrada vazia, digite a senha", String.class);

        
        if (usuarioService.entrar(nome, senha)) {
            System.out.println("Sucesso! Entrando no gerenciador de Tarefa.");
            System.out.println(usuarioService.getUsuarioAtual().getNomeDeUsuario()); //CONFIRMAÇÃO pode apagar dps
        }else {
            System.out.println("Usuário ou senha incorreto.");
        }

        System.out.println("=========================");
    }
    
}
