package validators;

import repository.UsuarioRepository;

public class NameValidator implements Validator<String> {
    private UsuarioRepository usuarioRepositorio;
    
    @Override
    public boolean validate(String data) {
        return usuarioRepositorio.existsUsuario(data);
    }
}
