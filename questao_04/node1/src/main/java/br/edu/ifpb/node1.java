package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class node1 {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",8081);

        for (int i = 0; i < 1000; i ++) {

            User user = new User(i+1, "Jozimar");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Mensagem enviada para node 2: " + user);
            objectOutputStream.writeObject(user);

        }

        socket.close();
    }
}
