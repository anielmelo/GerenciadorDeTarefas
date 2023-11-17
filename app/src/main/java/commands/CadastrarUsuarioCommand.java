package commands;

import repository.UsuarioRepository;
import service.UsuarioService;
import validators.NameValidator;
import validators.NonEmptyValidator;
import validators.ValidationContext;

public class CadastrarUsuarioCommand implements Command {
    @Override
    public void execute() {
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
        System.out.println("=========================");
        ValidationContext<String> strValidaNome = new ValidationContext<>(new NonEmptyValidator());
        String nome = strValidaNome.getValidValue("Nome: ", "Digite um nome para usuário", String.class);

        if (usuarioService.exists(nome)) {
            System.out.println("Usuário já cadastrado.");
        }else {
            ValidationContext<String> strValidaSenha = new ValidationContext<>(new NonEmptyValidator());
            String senha = strValidaSenha.getValidValue("Senha: ", "Caixa de entrada vazia, digite a senha", String.class);
            usuarioService.cadastrar(nome, senha);
            System.out.println("Usuário cadastrado com sucesso.");
        }
        System.out.println("=========================");

    }
}
