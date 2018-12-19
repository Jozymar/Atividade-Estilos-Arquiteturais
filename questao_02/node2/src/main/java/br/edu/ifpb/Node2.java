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
        System.out.println("Aguardando uma conexão...");

        //Criando socket
        Socket socket2 = serverSocket.accept();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket2.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket2.getInputStream());

        //Requisição recebida do cliente
        Requisicao requisicao = (Requisicao) objectInputStream.readObject();
        System.out.println("Mensagem recebida do cliente: " + requisicao);

        if (requisicao.getOperacao().equals("op1")) {
            Integer op1xy = operacao1(requisicao.getX(), requisicao.getY());

            objectOutputStream.writeObject(op1xy);
            System.out.println("Mensagem enviada para o cliente: " + op1xy);
        }

        socket2.close();
    }

    //Método que realiza a operação 1
    private static Integer operacao1(Integer x, Integer y){
        return  2 * y * x;
    }
}