/*package service;

import domain.Tarefa;
import repository.TarefaRepository;

import java.util.regex.Pattern;
import java.util.List;

public class TarefaService {
        TarefaRepository tarefaRepository;
        
        public TarefaService(TarefaRepository tarefaRepository) {
            this.tarefaRepository = tarefaRepository;
    }

    public void creat(String titulo, String descricao, String prioridade, String categoria, String prazo, boolean status) {
        Pattern dataFormat = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        
        if (dataFormat.matcher(prazo).matches()) {
            tarefaRepository.creat(new Tarefa(titulo, descricao, prioridade, categoria, prazo, status));
        } else {
            System.out.println("Data inválida");
        }
    }

    public List<Tarefa> getTasks(){
        return tarefaRepository.getTarefaBD();
    }

    
}

*/