package commands;

import repository.TarefaRepository;
import service.TarefaService;

public class ListarTarefasCommand implements Command {
    @Override
    public void execute() {
        TarefaService tarefaService = new TarefaService(TarefaRepository.getTarefaBD());
    }
}
