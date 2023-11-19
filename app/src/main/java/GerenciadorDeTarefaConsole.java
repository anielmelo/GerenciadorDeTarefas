import java.util.Scanner;

import commands.BuscarTarefaCommand;
import commands.CadastrarUsuarioCommand;
import commands.CommandExecutor;
import commands.CriarTarefaCommand;
import commands.EditarTarefaCommand;
import commands.ExcluirTarefaCommand;
import commands.FiltrarTarefaCommand;
import commands.ListarTarefasCommand;
import commands.LoginCommand;
import repository.UsuarioRepository;
import service.UsuarioService;

public class GerenciadorDeTarefaConsole {

    public static void mostrarMenuDeLogin() {
        UsuarioService service = new UsuarioService(UsuarioRepository.getInstance());
        CommandExecutor executor = new CommandExecutor();
        
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
        
        while(opcao != 3) {
            System.out.println("\nMENU DE LOGIN");
            System.out.println("[1] - Entrar");
            System.out.println("[2] - Cadastro");
            System.out.println("[3] - Sair");

            System.out.print("Digite a opção: ");
            opcao = leitor.nextInt();
            
            switch (opcao) {
                case 1:
                    executor.executeCommand(new LoginCommand());
                    if (service.getUsuarioAtual() != null) {
                        mostrarMenuDeTarefas();
                    }
                    break;
                case 2:
                    executor.executeCommand(new CadastrarUsuarioCommand());
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

    }

    public static void mostrarMenuDeTarefas() {
        UsuarioService service = new UsuarioService(UsuarioRepository.getInstance());
        CommandExecutor executor = new CommandExecutor();

        Scanner leitorTarefa = new Scanner(System.in);
        int opcaoTarefa = 0;

        while (opcaoTarefa != 7) {
            System.out.println("\nMENU DE TAREFAS");
            System.out.println("[1] Criar");
            System.out.println("[2] Listar");
            System.out.println("[3] Filtrar");
            System.out.println("[4] Buscar");
            System.out.println("[5] Editar");
            System.out.println("[6] Excluir");
            System.out.println("[7] Sair");

            opcaoTarefa = leitorTarefa.nextInt();

            switch (opcaoTarefa) {
                case 1:
                    executor.executeCommand(new CriarTarefaCommand());
                    break;
                case 2:
                    executor.executeCommand(new ListarTarefasCommand());
                    break;
                case 3:
                    executor.executeCommand(new FiltrarTarefaCommand());
                    break;
                case 4:
                    executor.executeCommand(new BuscarTarefaCommand());
                    break;
                case 5:
                    executor.executeCommand(new EditarTarefaCommand());
                    break;
                case 6:
                    executor.executeCommand(new ExcluirTarefaCommand());
                    break;
                case 7:
                    System.out.println("Saindo...");
                    service.sair();
                    mostrarMenuDeLogin();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    
    public static void main(String[] args) {
        mostrarMenuDeLogin();
    }
}