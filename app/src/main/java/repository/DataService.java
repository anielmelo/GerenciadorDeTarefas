package repository;

import domain.Tarefa;
import domain.Usuario;
import java.util.List;
import java.util.UUID;

public interface DataService {
    void createUsuario(Usuario usuario);
    List<Usuario> getAllUsuario();
    void createTarefa(String nomeUsuario, Tarefa tarefa);
    void updateTarefa(String nomeUsuario, Tarefa tarefa);
    void updateStatus(String nomeUsuario, Tarefa tarefa);
    void removeTarefa(String nomeUsuario, Tarefa tarefa);

    public Usuario access(String nome, String senha);
    public Usuario getUsuario(String nomeUsuario);
    public boolean existsUsuario(String nome);
    public List<Tarefa> getTarefas(String nomeUsuario);
    public Tarefa getTarefaEditavel(String nomeUsuario, UUID id);
    public List<Tarefa> searchTarefa(String nomeUsuario, String termo);
    public List<Tarefa> filterTarefa(String nomeUsuario, String prioridade);
}
