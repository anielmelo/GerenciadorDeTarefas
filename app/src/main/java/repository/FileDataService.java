package repository;

import domain.Tarefa;
import domain.Usuario;
import java.io.*;
import java.util.List;

public class FileDataService extends InMemoryRepository {
    
    public FileDataService() {
        File data = new File("data.bin");
        if (data.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.bin"))) {
                usuarioBD = (List<Usuario>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void createUsuario(Usuario usuario) {
        super.createUsuario(usuario);
        write();
    }

    @Override
    public void createTarefa(String nome, Tarefa tarefa) {
        super.createTarefa(nome, tarefa);
        write();
    }
    
    @Override
    public void updateTarefa(String nome, Tarefa tarefa) {
        super.updateTarefa(nome, tarefa);
        write();
    }
    
    @Override
    public void updateStatus(String nome, Tarefa tarefa) {
        super.updateStatus(nome, tarefa);
        write();
    }
    
    @Override
    public void removeTarefa(String nome, Tarefa tarefa) {
        super.removeTarefa(nome, tarefa);
        write();
    }

    private void write() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.bin"))) {
            oos.writeObject(getAllUsuario());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
