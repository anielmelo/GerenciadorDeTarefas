package commands;

import domain.Tarefa;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import repository.UsuarioRepository;
import service.UsuarioService;

public class ExcluirTarefaGUICommand implements Command {
    private final JTable tableTarefa;
    private final UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());

    public ExcluirTarefaGUICommand(JTable table) {
        this.tableTarefa = table;
    }

    @Override
    public void execute() {
        int indice = tableTarefa.getSelectedRow();
        
        if (indice == -1) {
            JOptionPane.showMessageDialog(tableTarefa.getParent(), "Selecione uma tarefa para remover!");
        } else {
            Tarefa tarefaSelecionada = usuarioService.listarTarefa().get(indice);
            usuarioService.removerTarefa(tarefaSelecionada);
            JOptionPane.showMessageDialog(tableTarefa.getParent(), "Tarefa removida com sucesso!");
        }
    }
}
