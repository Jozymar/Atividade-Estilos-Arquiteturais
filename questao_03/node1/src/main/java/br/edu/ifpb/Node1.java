package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket socket = new Socket("localhost",8081);
//        Socket socket1 = new Socket("localhost",8082);

        Requisicao requisicao1 = new Requisicao("op1",10, 20);
        Requisicao requisicao2 = new Requisicao("op2",5, 10);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Mensagem enviada para node 2: " + requisicao2);
        objectOutputStream.writeObject(requisicao2);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Integer mensagemRecebida = (Integer) objectInputStream.readObject();
        System.out.println("Mensagem recebida de node 2: " + mensagemRecebida);

//        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socket1.getOutputStream());
//        System.out.println("Mensagem enviada para node 3: " + requisicao1);
//        objectOutputStream1.writeObject(requisicao1);
//
//        ObjectInputStream objectInputStream1 = new ObjectInputStream(socket1.getInputStream());
//        Integer mensagemRecebida1 = (Integer) objectInputStream1.readObject();
//        System.out.println("Mensagem recebida de node 3: " + mensagemRecebida1);

        socket.close();
//        socket1.close();
    }
}
