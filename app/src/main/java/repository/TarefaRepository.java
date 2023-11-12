package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; //só por causa da versao do java, pode ser trocado para toList() se a versao for 16+

import domain.Tarefa;

public class TarefaRepository {
    private List<Tarefa> tarefaBD = new ArrayList<>();

    public List<Tarefa> getTarefaBD() {
        return tarefaBD;
    }

    public void setTarefaBD(List<Tarefa> tarefaBD) {
        this.tarefaBD = tarefaBD;
    }

    public void creat(Tarefa tarefa) {
        tarefaBD.add(tarefa);
    }

    public List<Tarefa> getAll() {
        return tarefaBD;
    }

    public List<Tarefa> search(String opcaoBuscar) {
            if (opcaoBuscar == null || opcaoBuscar.isEmpty())
                return new ArrayList<>();
    
            String termo = opcaoBuscar.toLowerCase().trim();
            List<Tarefa> resultado = tarefaBD.stream()
            .filter(t -> (t.getTitulo() + t.getDescricao()).toLowerCase().contains(termo))
            .collect(Collectors.toList());
            return resultado;
        }

    
}


