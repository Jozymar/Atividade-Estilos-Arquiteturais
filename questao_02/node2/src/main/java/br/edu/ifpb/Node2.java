package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8082);

        //Criando socket
        Socket socket2 = serverSocket.accept();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket2.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket2.getInputStream());

        Requisicao requisicao = (Requisicao) objectInputStream.readObject();
        System.out.println("Mensagem recebida do cliente: " + requisicao);

        if (requisicao.getOperacao().equals("op1")) {
            Integer op1xy = operacao1(requisicao.getY(), requisicao.getX());

            objectOutputStream.writeObject(op1xy);
            System.out.println("Mensagem enviada para o cliente: " + op1xy);
        }

        socket2.close();
    }

    private static Integer operacao1(Integer y, Integer x){
        return  2 * y * x;
    }
}