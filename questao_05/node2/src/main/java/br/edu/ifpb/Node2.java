package br.edu.ifpb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8082);
        System.out.println("Aguardando uma conexão...");

        //Criando socket
        Socket socket = serverSocket.accept();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Requisicao requisicao = (Requisicao) objectInputStream.readObject();
        System.out.println("Mensagem recebida: " + requisicao);

        if (requisicao.getOperacao().equals("op2")) {

            //Variável que armazena o resultado da operação 2
            Integer op2 = diff(requisicao.getX(), requisicao.getY());

            FileWriter fileWriter = new FileWriter("/home/jozimar/Documentos/repositorio/diff.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(op2.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();

        }

        socket.close();
    }

    //Método que realiza a operação diff
    private static Integer diff(Integer x, Integer y){
        return  x - y;
    }
}
