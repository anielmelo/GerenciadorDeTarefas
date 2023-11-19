package domain;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nomeDeUsuario;
    private String senhaDeUsuario;
    private List<Tarefa> listaDeTarefa;
    
    public Usuario(String nomeDeUsuario, String senhaDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senhaDeUsuario = senhaDeUsuario;
        this.listaDeTarefa = new ArrayList<>();
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }
    
    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }
    
    public String getSenhaDeUsuario() {
        return senhaDeUsuario;
    }
    
    public void setSenhaDeUsuario(String senhaDeUsuario) {
        this.senhaDeUsuario = senhaDeUsuario;
    }
    
    public List<Tarefa> getListaDeTarefa() {
        return listaDeTarefa;
    }

    public void setListaDeTarefa(List<Tarefa> listaDeTarefa) {
        this.listaDeTarefa = listaDeTarefa;
    }

    public void adicionarNovaTarefa(Tarefa novaTarefa) {
        listaDeTarefa.add(novaTarefa);
    }
}
