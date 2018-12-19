package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node4 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8083);
        System.out.println("Aguardando uma conexão...");

        //Criando socket
        Socket socket = serverSocket.accept();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        //Recebendo mensagem
        Requisicao requisicao = (Requisicao) objectInputStream.readObject();
        System.out.println("Mensagem recebida: " + requisicao);

        if (requisicao.getOperacao().equals("op1")) {

            Integer op1 = operacao1(requisicao.getX(), requisicao.getY());

            objectOutputStream.writeObject(op1);
            System.out.println("Mensagem enviada para node 2: " + op1);
        }

        if (requisicao.getOperacao().equals("op2")) {

            Integer op2 = operacao2(requisicao.getX(), requisicao.getY());

            objectOutputStream.writeObject(op2);
            System.out.println("Mensagem enviada para node 3: " + op2);
        }

        socket.close();

    }

    //Método que realiza a operação 1
    private static Integer operacao1(Integer x, Integer y){
        return  x + y;
    }

    //Método que realiza a operação 2
    private static Integer operacao2(Integer x, Integer y){
        return  x - y;
    }
}
