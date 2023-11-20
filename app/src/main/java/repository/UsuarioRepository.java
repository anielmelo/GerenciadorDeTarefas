package repository;

import java.util.List;
import java.util.UUID;

import domain.Tarefa;
import domain.Usuario;

public class UsuarioRepository {
    private static UsuarioRepository instance;
    private InMemoryRepository memory;

    private UsuarioRepository(InMemoryRepository memory) {
        this.memory = memory;
    }

    public static UsuarioRepository getInstance() {
        if (instance == null) {
            instance = new UsuarioRepository(new InMemoryRepository());
        }
        return instance;
    }

    public void setRpository(InMemoryRepository memory) {
        this.memory = memory;
    }

    // USUÁRIO
    public Usuario access(String nome, String senha) {
        return memory.access(nome, senha);
    }

    public Usuario getUsuario(String nomeUsuario) {
        return memory.getUsuario(nomeUsuario);
    }

    public void createUsuario(Usuario usuario) {
        memory.createUsuario(usuario);
    }

    public boolean existsUsuario(String nome) {
        return memory.existsUsuario(nome);
    }
    
    // TAREFAS
    public List<Tarefa> getTarefas(String nomeUsuario) {
        return memory.getTarefas(nomeUsuario);
    }

    public Tarefa getTarefaEditavel(String nomeUsuario, UUID id) {
        return memory.getTarefaEditavel(nomeUsuario, id);
    }

    public void updateStatus(String nomeUsuario, Tarefa tarefa) {
        memory.updateStatus(nomeUsuario, tarefa);
    }

    public void createTarefa(String nomeUsuario, Tarefa tarefa) {
        memory.createTarefa(nomeUsuario, tarefa);
    }
    
    public void updateTarefa(String nomeUsuario, Tarefa tarefa) {
        memory.updateTarefa(nomeUsuario, tarefa);
    }
    
    public void removeTarefa(String nomeUsuario, Tarefa tarefa) { 
        memory.removeTarefa(nomeUsuario, tarefa);
    }
    
    public List<Tarefa> searchTarefa(String nomeUsuario, String termo) {
        return memory.searchTarefa(nomeUsuario, termo);
    }

    public List<Tarefa> filterTarefa(String nomeUsuario, String prioridade) {
        return memory.filterTarefa(nomeUsuario, prioridade);
    }
}
