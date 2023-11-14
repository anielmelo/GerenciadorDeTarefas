package service;

import java.util.List;

import domain.Tarefa;
import domain.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository repository;
	
	private Usuario usuarioAtual;
	
	public UsuarioService(UsuarioRepository usuarioRepositorio) {
		this.repository = usuarioRepositorio;
	}
	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}
	
    public boolean entrar(String nome, String senha) {
		if (repository.access(nome, senha) == null) {
			return false;
		}
		return true;
	}

	public void cadastrar(String nome, String senha) {
		repository.createUsuario(new Usuario(nome, senha));
	}

	public void criarTarefa(String titulo, String descricao, String prioridade, String categoria, String prazo) {
		repository.createTarefa(getUsuarioAtual().getNomeDeUsuario(), new Tarefa(titulo, descricao, prioridade, categoria, prazo));
	}

	public void listarTarefa() {
		repository.getTarefas(getUsuarioAtual().getNomeDeUsuario());
	}

	public void atualizarTarefa(String titulo, String descricao, String prioridade, String categoria, String prazo) {
		repository.updateTarefa(usuarioAtual.getNomeDeUsuario(), new Tarefa(titulo, descricao, prioridade, categoria, prazo));
	}

	public List<Tarefa> buscarTarefa(String termo) {
		return repository.searchTarefa(usuarioAtual.getNomeDeUsuario(), termo);
	}
	
	public List<Tarefa> filtrarTarefa(String prioridade) {
		return null;
	}

	public void removerTarefa() {}

	public boolean exists(String nome) {
		return repository.existsUsuario(nome);
	}

}
