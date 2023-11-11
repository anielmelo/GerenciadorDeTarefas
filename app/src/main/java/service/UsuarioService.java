package service;

import domain.Usuario;
import repository.UsuarioRepository;

import java.util.Optional;

public class UsuarioService {
    private UsuarioRepository usuarioRepositorio;
    
    public void entrar(String nome, String senha) {
	Optional<Usuario> usuarioAtual = usuarioRepositorio.get(nome, senha);
	if (usuarioAtual.isPresent()) {
		tarefaRepositorio.setTarefaBD(usuario.getListaDeTarefa());
		return;
}
}

public void cadastrar(String nome, String senha) {
	usuarioRepositorio.create(new Usuario(nome, senha));
}

public boolean existe(String nome) {
	return usuarioRepositorio.exists(nome);	// TALVEZ REMOVER
}

    
}
