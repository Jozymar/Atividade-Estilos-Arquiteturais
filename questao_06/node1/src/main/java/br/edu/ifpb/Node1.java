package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("Aguardando notificações...");

        //Criando socket
        Socket socket = serverSocket.accept();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        while (true) {
            String notificacao = (String) objectInputStream.readObject();
            System.out.println("Notificação recebida: " + notificacao);
        }
    }
}
