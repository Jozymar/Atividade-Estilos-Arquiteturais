package br.edu.ifpb;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Node1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("Aguardando uma conexão...");

        //Criando socket
        Socket socket = serverSocket.accept();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Requisicao requisicao = (Requisicao) objectInputStream.readObject();
        System.out.println("Mensagem recebida: " + requisicao);

        if (requisicao.getOperacao().equals("op1")) {

            //Variável que armazena o resultado da operação 1
            Integer op1 = sum(requisicao.getX(), requisicao.getY());

            File sum_file = new File("/opt/shared/sum.txt");

            if (sum_file.exists()) {

                FileWriter fileWriter = new FileWriter(sum_file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write(op1.toString());
                bufferedWriter.newLine();
                bufferedWriter.close();

            } else {
                System.out.println("Arquivo não existe!");
            }

        }

        socket.close();
    }

    //Método que realiza a operação sum
    private static Integer sum(Integer x, Integer y){
        return  x + y;
    }
}
