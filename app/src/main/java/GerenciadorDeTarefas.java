import commands.CadastrarUsuarioCommand;
import commands.CommandExecutor;
import commands.LoginCommand;
import repository.UsuarioRepository;
import service.UsuarioService;

public class GerenciadorDeTarefas {
    
    public static void main(String[] args) {
        UsuarioRepository dataService = UsuarioRepository.getInstance();
        CommandExecutor executor = new CommandExecutor();
        
        executor.executeCommand(new CadastrarUsuarioCommand());
        executor.executeCommand(new LoginCommand());
    }
}
