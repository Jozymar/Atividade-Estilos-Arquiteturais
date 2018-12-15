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

        //Criando socket
        Socket socket = serverSocket.accept();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Requisicao requisicao = (Requisicao) objectInputStream.readObject();
        System.out.println("Mensagem recebida: " + requisicao);

        if (requisicao.getOperacao().equals("op1")) {
            Socket socket1 = new Socket("localhost", 8083);

            ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socket1.getOutputStream());
            ObjectInputStream objectInputStream1 = new ObjectInputStream(socket1.getInputStream());

            objectOutputStream1.writeObject(requisicao);
            System.out.println("Mensagem enviada para node 4: " + requisicao);

            Integer mensagemRecebida = (Integer) objectInputStream1.readObject();
            System.out.println("Mensagem recebida de node 4: " + mensagemRecebida);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(mensagemRecebida);
            System.out.println("Mensagem enviada para node 3 " + mensagemRecebida);

            socket1.close();
        }

        if (requisicao.getOperacao().equals("op2")) {
            Socket socket2 = new Socket("localhost", 8082);

            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());
            objectOutputStream2.writeObject(requisicao);
            System.out.println("Mensagem enviada para node 3: " + requisicao);

            ObjectInputStream objectInputStream2 = new ObjectInputStream(socket2.getInputStream());
            Integer mensagemRecebida = (Integer) objectInputStream2.readObject();
            System.out.println("Mensagem recebida de node 3: " + mensagemRecebida);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(mensagemRecebida);
            System.out.println("Mensagem enviada para node 1: " + mensagemRecebida);

            socket2.close();
        }

        socket.close();
    }
}
