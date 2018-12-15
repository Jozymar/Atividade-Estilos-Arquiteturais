package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Node3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8082);

        //Criando socket
        Socket socket = serverSocket.accept();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        Mensagem mensagem = null;
        try {
            mensagem = (Mensagem) objectInputStream.readObject();
            System.out.println("Mensagem recebida de node 2: " + mensagem);
        } catch (SocketException e) {
            System.out.println("Nenhuma mensagem recebida!");
            System.exit(0);
        }

        double fxy = ((Math.pow(mensagem.getNumero1(), mensagem.getNumero1())) + (Math.pow(mensagem.getNumero2(), mensagem.getNumero2())));

        objectOutputStream.writeObject(fxy);
        System.out.println("Mensagem enviada para node 2: " + fxy);
        socket.close();
    }
}
