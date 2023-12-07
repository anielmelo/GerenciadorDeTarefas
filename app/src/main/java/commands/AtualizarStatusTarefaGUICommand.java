package commands;

import domain.Tarefa;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import repository.UsuarioRepository;
import service.UsuarioService;

public class AtualizarStatusTarefaGUICommand implements Command {
    private final JTable tableTarefa;
    private final UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
    
    public AtualizarStatusTarefaGUICommand(JTable table) {
        this.tableTarefa = table;
    }

    @Override
    public void execute() {
        int indice = tableTarefa.getSelectedRow();
        
        if (indice == -1) {
            JOptionPane.showMessageDialog(tableTarefa.getParent(), "Selecione uma tarefa para atualizar o status!");
        } else {
            Tarefa tarefaSelecionada = usuarioService.listarTarefa().get(indice);
            usuarioService.atualizarStatus(tarefaSelecionada);
            JOptionPane.showMessageDialog(tableTarefa.getParent(), "Status atualizado com sucesso!");
        }
    }
    
    
}
