package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.Usuario;

public class UsuarioRepository {
    private List<Usuario> usuarioBD = new ArrayList<>();
        
    public Optional<Usuario> get(String nome, String senha) {
    return usuarioBD.stream().filter(usuario -> usuario.getNomeDeUsuario().equals(nome) && usuario.getSenhaDeUsuario().equals(senha)).findFirst();
    }

    public void create(Usuario usuario) {
        usuarioBD.add(usuario);
    }

    public boolean exists(String nome) {
        return usuarioBD.stream().anyMatch(usuario -> usuario.getNomeDeUsuario().equals(nome));
    }

    
}
