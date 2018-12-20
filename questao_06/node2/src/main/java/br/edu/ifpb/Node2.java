package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.*;

public class Node2 {

    public static void main(String[] args) throws IOException {

        //Criando socket
        Socket socket = new Socket("localhost",8081);
        System.out.println("Verificando...");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        Path path = Paths.get("./disk");
        WatchService watcher = FileSystems.getDefault().newWatchService();

        path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
        while (true){
            WatchKey key = null;
            try{
                key = watcher.take();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
            for (WatchEvent<?> event : key.pollEvents()){
                String eventName = event.context().toString();

                if (eventName.equals("banco.txt")){
                    System.out.println("update");
                    System.out.println("Notificação enviada para node 1");
                    objectOutputStream.writeObject("update disk");
                }
            }
            key.reset();
        }
    }
}
