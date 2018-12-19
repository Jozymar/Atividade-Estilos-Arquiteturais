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
        System.out.println("Aguardando uma conexão...");

        //Criando sockets
        Socket socket1 = serverSocket.accept();
        Socket socket2 = new Socket("localhost", 8082);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket1.getInputStream());

        //Recebendo mensagem de node 1
        Mensagem mensagem = (Mensagem) objectInputStream.readObject();
        System.out.println("Mensagem recebida de node 1: " + mensagem);

        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());

        if (mensagem.getNumero1().equals(mensagem.getNumero2())) {
            //Enviando resposta para node 1
            objectOutputStream.writeObject(new Double(0));
        } else {
            //Enviando resposta para node 3
            objectOutputStream2.writeObject(mensagem);

            ObjectInputStream objectInputStream2 = new ObjectInputStream(socket2.getInputStream());

            //Recebendo mensagem de node 3
            Double mensagemRecebida = (Double) objectInputStream2.readObject();
            System.out.println("Mensagem recebida de node 3: " + mensagemRecebida);

            //Enviando mensagem para node 1
            objectOutputStream.writeObject(mensagemRecebida);
        }

        socket1.close();
        socket2.close();
    }
}