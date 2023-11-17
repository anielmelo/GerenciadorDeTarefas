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
        System.out.println("\n=========================");
        ValidationContext<String> strValidaNome = new ValidationContext<>(new NameLoginValidator());
        String nome = strValidaNome.getValidValue("Nome: ", "Usuário não encontrado.", String.class);

        ValidationContext<String> strValidaSenha = new ValidationContext<>(new NonEmptyValidator());
        String senha = strValidaSenha.getValidValue("Senha: ", "Caixa de entrada vazia, digite a senha", String.class);

        usuarioService.entrar(nome, senha);
    }

}
