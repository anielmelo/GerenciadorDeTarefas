package commands;

import domain.Usuario;
import gui.TarefaWindow;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import repository.UsuarioRepository;
import service.UsuarioService;
import validators.GUITextValidator;
import validators.NonEmptyValidator;

public class LoginGUICommand implements Command {
    private final JTextField txtNomeUsuario;
    private final JPasswordField txtSenhaUsuario;
    private final JFrame frame;
    
    private final UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());

    public LoginGUICommand(JFrame frame, JTextField nome, JPasswordField senha) {
        this.frame = frame;
        this.txtNomeUsuario = nome;
        this.txtSenhaUsuario = senha;
    }
    
    @Override
    public void execute() {
        String nomeStr = txtNomeUsuario.getText();
        String senhaStr = txtSenhaUsuario.getSelectedText();

        GUITextValidator nomeValidator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator senhaValidator = new GUITextValidator(new NonEmptyValidator());

        boolean nomeIsValid = nomeValidator.validate(txtNomeUsuario);
        boolean senhaIsValid = senhaValidator.validate(txtSenhaUsuario);

        if (nomeIsValid && senhaIsValid) {
            Usuario usuario = usuarioService.entrar(nomeStr, senhaStr);
            if (usuario != null) {
                JOptionPane.showMessageDialog(txtNomeUsuario.getParent(), String.format("Bem vindo(a), %s!", nomeStr));
                frame.setVisible(false);
                TarefaWindow tarefa = new TarefaWindow();
                tarefa.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(txtNomeUsuario.getParent(), "Usuário não caadastrado!");
            }
        }
    }
}
