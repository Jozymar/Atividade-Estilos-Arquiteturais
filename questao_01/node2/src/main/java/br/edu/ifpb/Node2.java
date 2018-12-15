package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8081);

        //Criando sockets
        Socket socket = serverSocket.accept();
        Socket socket2 = new Socket("localhost", 8082);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        Mensagem mensagem = (Mensagem) objectInputStream.readObject();
        System.out.println("Mensagem recebida de node 1: " + mensagem);

        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());

        if (mensagem.getNumero1().equals(mensagem.getNumero2())) {
            objectOutputStream.writeObject(new Double(0));
        } else {
            objectOutputStream2.writeObject(mensagem);

            ObjectInputStream objectInputStream2 = new ObjectInputStream(socket2.getInputStream());
            Double mensagemRecebida = (Double) objectInputStream2.readObject();
            System.out.println("Mensagem recebida de node 3: " + mensagemRecebida);

            objectOutputStream.writeObject(mensagemRecebida);
        }

        socket.close();
        socket2.close();
    }
}