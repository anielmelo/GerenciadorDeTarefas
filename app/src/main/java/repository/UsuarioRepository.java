package repository;

import java.util.List;
import java.util.UUID;

import domain.Tarefa;
import domain.Usuario;

public class UsuarioRepository {
    private DataService service;
    private static UsuarioRepository instance;

    private UsuarioRepository(DataService service) {
        this.service = service;
    }

    public static UsuarioRepository getInstance() {
        if (instance == null) {
            instance = new UsuarioRepository(new InMemoryRepository());
        }
        return instance;
    }

    public void setRepository(DataService service) {
        this.service = service;
    }

    // USUÁRIO
    public Usuario access(String nome, String senha) {
        return service.access(nome, senha);
    }

    public Usuario getUsuario(String nomeUsuario) {
        return service.getUsuario(nomeUsuario);
    }

    public void createUsuario(Usuario usuario) {
        service.createUsuario(usuario);
    }

    public boolean existsUsuario(String nome) {
        return service.existsUsuario(nome);
    }
    
    // TAREFAS
    public List<Tarefa> getTarefas(String nomeUsuario) {
        return service.getTarefas(nomeUsuario);
    }

    public Tarefa getTarefaEditavel(String nomeUsuario, UUID id) {
        return service.getTarefaEditavel(nomeUsuario, id);
    }

    public void updateStatus(String nomeUsuario, Tarefa tarefa) {
        service.updateStatus(nomeUsuario, tarefa);
    }

    public void createTarefa(String nomeUsuario, Tarefa tarefa) {
        service.createTarefa(nomeUsuario, tarefa);
    }
    
    public void updateTarefa(String nomeUsuario, Tarefa tarefa) {
        service.updateTarefa(nomeUsuario, tarefa);
    }
    
    public void removeTarefa(String nomeUsuario, Tarefa tarefa) { 
        service.removeTarefa(nomeUsuario, tarefa);
    }
    
    public List<Tarefa> searchTarefa(String nomeUsuario, String termo) {
        return service.searchTarefa(nomeUsuario, termo);
    }

    public List<Tarefa> filterTarefa(String nomeUsuario, String prioridade) {
        return service.filterTarefa(nomeUsuario, prioridade);
    }
}
