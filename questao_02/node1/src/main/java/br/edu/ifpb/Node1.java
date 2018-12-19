package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("Aguardando uma conexão...");

        //Criando socket
        Socket socket1 = serverSocket.accept();

        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socket1.getOutputStream());
        ObjectInputStream objectInputStream1 = new ObjectInputStream(socket1.getInputStream());

        //Requisição recebida do cliente
        Requisicao requisicao = (Requisicao) objectInputStream1.readObject();
        System.out.println("Mensagem recebida do cliente: " + requisicao);

        if (requisicao.getOperacao().equals("op1")) {
            Integer op1xy = operacao1(requisicao.getX(), requisicao.getY());

            objectOutputStream1.writeObject(op1xy);
            System.out.println("Mensagem enviada para o cliente: " + op1xy);

            socket1.close();
        } else {

            //Criando socket
            Socket socket3 = new Socket("localhost", 8083);

            ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(socket3.getOutputStream());
            ObjectInputStream objectInputStream3 = new ObjectInputStream(socket3.getInputStream());

            objectOutputStream3.writeObject(requisicao);
            System.out.println("Mensagem enviada para node 3: " + requisicao);

            Integer mensagemRecebida = (Integer) objectInputStream3.readObject();
            System.out.println("Mensagem recebida de node 3: " + mensagemRecebida);

            objectOutputStream1.writeObject(mensagemRecebida);
            System.out.println("Mensagem enviada para o cliente: " + mensagemRecebida);

            socket3.close();
        }
    }

    //Método que realiza a operação 1
    private static Integer operacao1(Integer x, Integer y){
        return  2 * y * x;
    }
}