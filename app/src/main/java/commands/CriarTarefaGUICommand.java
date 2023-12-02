package commands;

import gui.CriarTarefaWindow;
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

public class CriarTarefaGUICommand implements Command {

    private final JTextField txtTitulo;
    private final JTextArea txtDescricao;
    private final JComboBox<String> cmbPrioridade;
    private final JTextField txtPrioridade;
    private final JTextField txtCategoria;
    private final JFormattedTextField txtData;
    private final CriarTarefaWindow criarTarefa;
    
    private final UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());

    public CriarTarefaGUICommand(JTextField txtTitulo, JTextField txtCategoria, JTextArea txtDescricao, JFormattedTextField txtData, JComboBox<String> cmbPrioridade, javax.swing.JTextField txtPrioridade, CriarTarefaWindow criarTarefa) {
        this.txtTitulo = txtTitulo;
        this.txtCategoria = txtCategoria;
        this.txtDescricao = txtDescricao;
        this.txtData = txtData;
        this.cmbPrioridade = cmbPrioridade;
        this.txtPrioridade = txtPrioridade;
        this.criarTarefa = criarTarefa;
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
        GUITextValidator prazoValidator = new GUITextValidator(new DateValidator());
        
        boolean tituloIsValid = tituloValidator.validate(txtTitulo);
        boolean descricaoIsValid = descricaoValidator.validate(txtDescricao);
        boolean prioridadeIsValid = prioridadeValidator.validate(txtPrioridade);
        boolean categoriaIsValid = categoriaValidator.validate(txtCategoria);
        boolean prazoIsValid = prazoValidator.validate(txtData);
        
        if (tituloIsValid && descricaoIsValid && prioridadeIsValid && categoriaIsValid && prazoIsValid) {
            usuarioService.criarTarefa(titulo, descricao, prioridade, categoria, prazo);
            JOptionPane.showMessageDialog(txtTitulo.getParent(), "Tarefa criada com sucesso!");
            criarTarefa.clearField();
            criarTarefa.dispose();
        } else {
            JOptionPane.showMessageDialog(txtTitulo.getParent(), "Erro! Preencha o formulário corretamente!");
        }
    }
}
