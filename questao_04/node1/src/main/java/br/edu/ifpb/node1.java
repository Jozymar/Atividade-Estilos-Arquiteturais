package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class node1 {

    public static void main(String[] args) throws IOException {

        //Criando socket
        Socket socket = new Socket("localhost",8081);

        //Para realizar os testes altere o valor de 'i'
        //(i < 100, i < 1000)
        for (int i = 0; i < 1000; i ++) {

            User user = new User(i+1, "Jozimar");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);

        }
        System.out.println("Mensagens enviadas para node 2.");

        socket.close();
    }
}
