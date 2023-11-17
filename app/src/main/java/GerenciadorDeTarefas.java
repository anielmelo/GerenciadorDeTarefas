import java.util.Scanner;

import commands.*;
import repository.UsuarioRepository;
import service.UsuarioService;

public class GerenciadorDeTarefas {
    
    public static void main(String[] args) {
        UsuarioRepository dataService = UsuarioRepository.getInstance();
        CommandExecutor executor = new CommandExecutor();
        UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
        boolean menuGerenciador = true;
        boolean menuTarefa = false;
        Scanner leitor = new Scanner(System.in);

        while (menuGerenciador) {
            System.out.println("[GERENCIADOR DE TAREFAS]");
            System.out.println("[1] - Login\n[2] - Cadastrar\n[3] - Sair");
            System.out.print("Digite a opção -> ");
            String opcao = leitor.nextLine();
            System.out.println();

            switch (opcao) {
                case "1" -> executor.executeCommand(new LoginCommand());
                case "2" -> executor.executeCommand(new CadastrarUsuarioCommand());
                case "3" -> {
                    menuGerenciador = false;
                    System.out.println("Finalizando programa...");
                }
                default -> System.out.println("Opção inválida.\n");
            }

            /*  VERIFICAR AQUI PQ ELE ESTÁ SEMPRE NULL (Eu ja fiz o set no loginCommand)
                Acho que ele tá adcionando na lista certa, acho que ele ta tipo criando novas listas toda vez que instancia
                Apaga isso depois...
            */
            if (usuarioService.getUsuarioAtual() != null) {
                System.out.printf("Bem vindo %s", usuarioService.getUsuarioAtual().getNomeDeUsuario());
                menuGerenciador = false;
                menuTarefa = true;
            }else {
                System.out.println("NULL");
            }
        }
    }
}
