package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8083);

        //Criando socket
        Socket socket3 = serverSocket.accept();

        ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(socket3.getOutputStream());
        ObjectInputStream objectInputStream3 = new ObjectInputStream(socket3.getInputStream());

        Requisicao requisicao = (Requisicao) objectInputStream3.readObject();
        System.out.println("Mensagem recebida do cliente: " + requisicao);

        if (requisicao.getOperacao().equals("op2")) {

            Integer op2xy = operacao2(requisicao.getX(), requisicao.getY());

            objectOutputStream3.writeObject(op2xy);
            System.out.println("Mensagem enviada para o cliente: " + op2xy);

            socket3.close();

        } else {

            Integer enviar = 1 + (int) (Math.random() * 2);

            if (enviar.equals(1)) {

                //Criando socket
                Socket socket1 = new Socket("localhost", 8081);

                ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socket1.getOutputStream());
                ObjectInputStream objectInputStream1 = new ObjectInputStream(socket1.getInputStream());

                objectOutputStream1.writeObject(requisicao);
                System.out.println("Mensagem enviada para node 1: " + requisicao);

                Integer mensagemRecebida = (Integer) objectInputStream1.readObject();
                System.out.println("Mensagem recebida de node 1: " + mensagemRecebida);

                objectOutputStream3.writeObject(mensagemRecebida);
                System.out.println("Mensagem enviada para o cliente: " + mensagemRecebida);

                socket1.close();

            } else {
                //Criando socket
                Socket socket2 = new Socket("localhost", 8082);

                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());
                ObjectInputStream objectInputStream2 = new ObjectInputStream(socket2.getInputStream());

                objectOutputStream2.writeObject(requisicao);
                System.out.println("Mensagem enviada para node 2: " + requisicao);

                Integer mensagemRecebida = (Integer) objectInputStream2.readObject();
                System.out.println("Mensagem recebida de node 2: " + mensagemRecebida);

                objectOutputStream3.writeObject(mensagemRecebida);
                System.out.println("Mensagem enviada para o cliente: " + mensagemRecebida);

                socket2.close();

            }

        }
    }

    private static Integer operacao2(Integer y, Integer x){
        return  2 * x / y;
    }
}
