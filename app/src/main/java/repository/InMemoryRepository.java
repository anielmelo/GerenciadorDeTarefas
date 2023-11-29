package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import domain.Tarefa;
import domain.Usuario;

public class InMemoryRepository {
    protected List<Usuario> usuarioBD = new ArrayList<>();
        
    // USUÁRIO
    public Usuario access(String nome, String senha) {
        Optional<Usuario> usuario = usuarioBD.stream().filter(u -> u.getNomeDeUsuario().equals(nome) && u.getSenhaDeUsuario().equals(senha)).findFirst();
        return usuario.isPresent() ? usuario.get() : null;
    }

    public Usuario getUsuario(String nomeUsuario) {
        Optional<Usuario> usuario = usuarioBD.stream().filter(u -> u.getNomeDeUsuario().equals(nomeUsuario)).findFirst();
        return usuario.isPresent() ? usuario.get() : null;
    }

    public void createUsuario(Usuario usuario) {
        usuarioBD.add(usuario);
    }

    public boolean existsUsuario(String nome) {
        if (usuarioBD.isEmpty()) {
            return false;
        }
        return usuarioBD.stream().anyMatch(usuario -> usuario.getNomeDeUsuario().equals(nome));
        
    }
    
    // TAREFAS
    public List<Tarefa> getTarefas(String nomeUsuario) {
        return getUsuario(nomeUsuario).getListaDeTarefa();
    }

    public Tarefa getTarefaEditavel(String nomeUsuario, UUID id) {
	Optional<Tarefa> tarefaEdit =  getTarefas(nomeUsuario).stream().filter(t -> t.getId().equals(id)).findFirst();
        return tarefaEdit.isPresent() ? tarefaEdit.get() : null;
    }

    public void createTarefa(String nomeUsuario, Tarefa tarefa) {
        getTarefas(nomeUsuario).add(tarefa);
    }
    
    public void updateTarefa(String nomeUsuario, Tarefa tarefa) {
        int index = getTarefas(nomeUsuario).indexOf(tarefa);
        getTarefas(nomeUsuario).set(index, tarefa);
    }
    
    public void removeTarefa(String nomeUsuario, Tarefa tarefa) {
        getTarefas(nomeUsuario).remove(tarefa);
    }

    public void updateStatus(String nomeUsuario, Tarefa tarefa) {
        int index = getTarefas(nomeUsuario).indexOf(tarefa);
        Tarefa tarefaTemp = getTarefas(nomeUsuario).get(index);
        tarefaTemp.setStatus(!(tarefaTemp.isStatus()));
    }
    
    public List<Tarefa> searchTarefa(String nomeUsuario, String termo) {
        return getTarefas(nomeUsuario).stream().filter(lista -> 
        lista.getTitulo().toLowerCase().contains(termo.toLowerCase()) || lista.getDescricao().contains(termo.toLowerCase())).toList();
    }

    public List<Tarefa> filterTarefa(String nomeUsuario, String prioridade) {
        return getTarefas(nomeUsuario).stream().filter(lista -> 
        lista.getPrioridade().equals(prioridade)).toList();
    }
}
