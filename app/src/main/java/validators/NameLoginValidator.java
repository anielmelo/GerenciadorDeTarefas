package validators;

import repository.UsuarioRepository;
import service.UsuarioService;

public class NameLoginValidator implements Validator<String> {
    private UsuarioService usuarioService = new UsuarioService(UsuarioRepository.getInstance());
    
    @Override
    public boolean validate(String data) {
        return usuarioService.exists(data);
    }
}
