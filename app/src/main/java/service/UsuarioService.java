package service;

import java.util.List;
import java.util.UUID;

import domain.Tarefa;
import domain.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository repository;
    private static Usuario usuarioAtual;

    public UsuarioService(UsuarioRepository usuarioRepositorio) {
        this.repository = usuarioRepositorio;
    }
    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setUsuarioAtual(Usuario usuarioAtual) {
        UsuarioService.usuarioAtual = usuarioAtual;
    }
	
    public Usuario entrar(String nome, String senha) {
        if (repository.access(nome, senha) != null) {
            setUsuarioAtual(repository.getUsuario(nome));
        }
        return getUsuarioAtual();
    }

    public void sair() {
        setUsuarioAtual(null);
    }

    public void cadastrar(String nome, String senha) {
        repository.createUsuario(new Usuario(nome, senha));
    }

    public void criarTarefa(String titulo, String descricao, String prioridade, String categoria, String prazo) {
        repository.createTarefa(getUsuarioAtual().getNomeDeUsuario(), new Tarefa(titulo, descricao, prioridade, categoria, prazo));
    }

    public Tarefa getTarefaID(UUID id) {
        return repository.getTarefaEditavel(getUsuarioAtual().getNomeDeUsuario(), id);
    }

    public List<Tarefa> listarTarefa() {
        return repository.getTarefas(getUsuarioAtual().getNomeDeUsuario());
    }

    public void atualizarStatus(Tarefa tarefa) {
        repository.updateStatus(getUsuarioAtual().getNomeDeUsuario(), tarefa);
    }

    public void atualizarTarefa(UUID id, String titulo, String descricao, String prioridade, String categoria, String prazo) {
        repository.updateTarefa(usuarioAtual.getNomeDeUsuario(), new Tarefa(getTarefaID(id).getId(), titulo, descricao, prioridade, categoria, prazo));
    }

    public List<Tarefa> buscarTarefa(String termo) {
        return repository.searchTarefa(usuarioAtual.getNomeDeUsuario(), termo);
    }

    public List<Tarefa> filtrarTarefa(String prioridade) {
        return repository.filterTarefa(getUsuarioAtual().getNomeDeUsuario(), prioridade);
    }

    public void removerTarefa(Tarefa tarefa) {
        repository.removeTarefa(getUsuarioAtual().getNomeDeUsuario(), tarefa);
    }

    public boolean exists(String nome) {
        return repository.existsUsuario(nome);
    }
}
