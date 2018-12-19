package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Node0 {

    public static void main(String[] args) throws IOException {

        //Requisição da operação 1
        Requisicao requisicao1 = new Requisicao("op1", 10, 20);

        //Requisição da operação 2
        Requisicao requisicao2 = new Requisicao("op2", 20, 10);

        //Criando socket
        Socket socket1 = new Socket("localhost",8081);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());
        System.out.println("Mensagem enviada para node 1: " + requisicao1);
        objectOutputStream.writeObject(requisicao1);

        socket1.close();

        //Criando socket
        Socket socket2 = new Socket("localhost",8082);

        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());
        System.out.println("Mensagem enviada para node 2: " + requisicao2);
        objectOutputStream2.writeObject(requisicao2);

        socket2.close();
    }


}
