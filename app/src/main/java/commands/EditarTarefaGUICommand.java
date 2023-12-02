package commands;

import domain.Tarefa;
import gui.EditarTarefaWindow;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import repository.UsuarioRepository;
import service.UsuarioService;
import validators.DateValidator;
import validators.GUITextValidator;
import validators.NonEmptyValidator;

public class EditarTarefaGUICommand implements Command {

    private final Tarefa tarefaSelecionada;
    private final JTextField txtTitulo;
    private final JTextArea txtDescricao;
    private final JComboBox<String> cmbPrioridade;
    private final JTextField txtPrioridade;
    private final JTextField txtCategoria;
    private final JFormattedTextField txtData;
    private final EditarTarefaWindow editarTarefa;
    
    private final UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());

    public EditarTarefaGUICommand(Tarefa tarefa, JTextField txtTitulo, JTextArea txtDescricao, JComboBox<String> cmbPrioridade, JTextField txtPrioridade, JTextField txtCategoria, JFormattedTextField txtData, EditarTarefaWindow editarTarefa) {
        this.tarefaSelecionada = tarefa;
        this.txtTitulo = txtTitulo;
        this.txtDescricao = txtDescricao;
        this.cmbPrioridade = cmbPrioridade;
        this.txtPrioridade = txtPrioridade;
        this.txtCategoria = txtCategoria;
        this.txtData = txtData;
        this.editarTarefa = editarTarefa;
    }
    
    @Override
    public void execute() {
        String titulo = txtTitulo.getText();
        String descricao = txtDescricao.getText();

        txtPrioridade.setText(cmbPrioridade.getItemAt(cmbPrioridade.getSelectedIndex()));
        String prioridade = txtPrioridade.getText();

        String categoria = txtCategoria.getText();
        String prazo = txtData.getText();

        GUITextValidator tituloValidator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator descricaoValidator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator prioridadeValidator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator categoriaValidator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator prazoValidator = new GUITextValidator(new NonEmptyValidator());

        boolean tituloIsValid = tituloValidator.validate(txtTitulo);
        boolean descricaoIsValid = descricaoValidator.validate(txtDescricao);
        boolean prioridadeIsValid = prioridadeValidator.validate(txtPrioridade);
        boolean categoriaIsValid = categoriaValidator.validate(txtCategoria);
        boolean prazoIsValid = prazoValidator.validate(txtData);

        if (tituloIsValid && descricaoIsValid && prioridadeIsValid && categoriaIsValid && prazoIsValid) {
            tarefaSelecionada.setTitulo(titulo);
            tarefaSelecionada.setDescricao(descricao);
            tarefaSelecionada.setPrioridade(prioridade);
            tarefaSelecionada.setCategoria(categoria);
            tarefaSelecionada.setPrazoDeConclusao(prazo);
            
            usuarioService.atualizarTarefa(tarefaSelecionada.getId(), tarefaSelecionada.getTitulo(), tarefaSelecionada.getDescricao(), tarefaSelecionada.getPrioridade(), tarefaSelecionada.getCategoria(), tarefaSelecionada.getPrazoDeConclusao());
            JOptionPane.showMessageDialog(txtTitulo.getParent(), "Tarefa editada com sucesso!");
            editarTarefa.clearField();
            editarTarefa.dispose();
        } else {
            JOptionPane.showMessageDialog(txtTitulo.getParent(), "Erro! Preencha o formulário corretamente!");
        }
    }
}
