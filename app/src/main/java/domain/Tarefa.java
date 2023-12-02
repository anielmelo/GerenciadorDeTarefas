package domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Tarefa implements Serializable {
    
    private final UUID id;
    private String titulo;
    private String descricao;
    private String prioridade;
    private String categoria;
    private String prazoDeConclusao;
    private boolean status;
    
    public Tarefa(String titulo, String descricao, String prioridade, String categoria, String prazoDeConclusao) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.prazoDeConclusao = prazoDeConclusao;
        this.status = false;
    }
    
    public Tarefa(UUID id, String titulo, String descricao, String prioridade, String categoria, String prazoDeConclusao, boolean status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.prazoDeConclusao = prazoDeConclusao;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrazoDeConclusao() {
        return prazoDeConclusao;
    }

    public void setPrazoDeConclusao(String prazoDeConclusao) {
        this.prazoDeConclusao = prazoDeConclusao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tarefa tarefa)) return false;
        return Objects.equals(getId(), tarefa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public String toString() {
        return String.format("TÍTULO: %s\n" + //
                "DESCRIÇÃO: %s\n" + //
                "PRIORIDADE: %s\n" + //
                "CATEGORIA: %s\n" + //
                "PRAZO: %s\n" + //
                "STATUS DE CONCLUSÃO: %s", getTitulo(), getDescricao(), getPrioridade(), getCategoria(), getPrazoDeConclusao(), isStatus()?"Concluída":"Pendente");
            }
}
