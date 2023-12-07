package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements Serializable {

    private final String nomeDeUsuario;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getNomeDeUsuario(), usuario.getNomeDeUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNomeDeUsuario());
    }
}
