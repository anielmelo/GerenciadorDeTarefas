package commands;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import repository.UsuarioRepository;
import service.UsuarioService;
import validators.GUITextValidator;
import validators.NonEmptyValidator;

public class CadastrarUsuarioGUICommand implements Command {

    private final JTextField txtNomeUsuario;
    private final JPasswordField txtSenhaUsuario;
    private final UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());

    public CadastrarUsuarioGUICommand(JTextField nome, JPasswordField senha) {
        this.txtNomeUsuario = nome;
        this.txtSenhaUsuario = senha;
    }
    
    @Override
    public void execute() {
        String nomeStr = txtNomeUsuario.getText();
        String senhaStr = new String(txtSenhaUsuario.getPassword());

        GUITextValidator nomeValidator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator senhaValidator = new GUITextValidator(new NonEmptyValidator());

        boolean nomeIsValid = nomeValidator.validate(txtNomeUsuario);
        boolean senhaIsValid = senhaValidator.validate(txtSenhaUsuario);

        if (usuarioService.exists(nomeStr)) {
            JOptionPane.showMessageDialog(txtNomeUsuario.getParent(), "Usuário já cadastrado!");
        } else {
            if (nomeIsValid && senhaIsValid) {
                JOptionPane.showMessageDialog(txtNomeUsuario.getParent(), "Usuário cadastrado com sucesso!");
                usuarioService.cadastrar(nomeStr, senhaStr);
            }
        }

    }
}
