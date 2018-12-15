package br.edu.ifpb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8081);

        //Criando socket
        Socket socket = serverSocket.accept();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Requisicao requisicao = (Requisicao) objectInputStream.readObject();
        System.out.println("Mensagem recebida: " + requisicao);

        if (requisicao.getOperacao().equals("op1")) {

            Integer op1 = sum(requisicao.getX(), requisicao.getY());

            FileWriter fileWriter = new FileWriter("/home/jozimar/Documentos/repositorio/sum.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(op1.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();

        }

        socket.close();
    }

    private static Integer sum(Integer x, Integer y){
        return  x + y;
    }
}
