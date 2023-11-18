package commands;

import repository.UsuarioRepository;
import service.UsuarioService;
import validators.NonEmptyValidator;
import validators.ValidationContext;

public class CadastrarUsuarioCommand implements Command {
    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
        
        System.out.println("\n=========================");
        ValidationContext<String> strValidaNome = new ValidationContext<>(new NonEmptyValidator());
        String nome = strValidaNome.getValidValue("Nome: ", "Caixa de entrada vazia, digite o nome.", String.class);

        ValidationContext<String> strValidaSenha = new ValidationContext<>(new NonEmptyValidator());
        String senha = strValidaSenha.getValidValue("Senha: ", "Caixa de entrada vazia, digite a senha.", String.class);

        if (usuarioService.exists(nome)) {
            System.out.println("Usuário já cadastrado.");
            return;
        }

        usuarioService.cadastrar(nome, senha);
    }
}
