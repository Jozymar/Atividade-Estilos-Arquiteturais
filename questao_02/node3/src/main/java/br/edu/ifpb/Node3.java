package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8083);
        System.out.println("Aguardando uma conexão...");

        //Criando socket
        Socket socket3 = serverSocket.accept();

        boolean node1Disponivel;
        boolean node2Disponivel;

        ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(socket3.getOutputStream());
        ObjectInputStream objectInputStream3 = new ObjectInputStream(socket3.getInputStream());

        //Requisição recebida do cliente
        Requisicao requisicao = (Requisicao) objectInputStream3.readObject();
        System.out.println("Mensagem recebida do cliente: " + requisicao);

        if (requisicao.getOperacao().equals("op2")) {

            Integer op2xy = operacao2(requisicao.getX(), requisicao.getY());

            objectOutputStream3.writeObject(op2xy);
            System.out.println("Mensagem enviada para o cliente: " + op2xy);

            socket3.close();

        } else {

            //Números gerados para representar o envio da operação 1 caso seja recebida
            //Como Node 3 pode delegar a operação 1 para Node 1 ou Node 2 são gerados numeros que representam os nodes
            //(Node1 = 1 e Node2 = 2)
            Integer enviar = 1 + (int) (Math.random() * 2);

            if (enviar.equals(1)) {

                //Criando socket
                Socket socket1 = new Socket();

                try {
                    socket1.connect(new InetSocketAddress("localhost", 8081));
                    node1Disponivel = true;

                }catch (IOException e) {
                    node1Disponivel = false;
                }

                if (node1Disponivel) {

                    ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socket1.getOutputStream());
                    ObjectInputStream objectInputStream1 = new ObjectInputStream(socket1.getInputStream());

                    objectOutputStream1.writeObject(requisicao);
                    System.out.println("Mensagem enviada para node 1: " + requisicao);

                    Integer mensagemRecebida = (Integer) objectInputStream1.readObject();
                    System.out.println("Mensagem recebida de node 1: " + mensagemRecebida);

                    objectOutputStream3.writeObject(mensagemRecebida);
                    System.out.println("Mensagem enviada para o cliente: " + mensagemRecebida);

                } else {
                    System.out.println("Node 1 não está disponível");
                }
                socket1.close();

            } else {
                //Criando socket
                Socket socket2 = new Socket();

                try {
                    socket2.connect(new InetSocketAddress("localhost", 8082));
                    node2Disponivel = true;

                }catch (IOException e) {
                    node2Disponivel = false;
                }

                if (node2Disponivel) {

                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());
                    ObjectInputStream objectInputStream2 = new ObjectInputStream(socket2.getInputStream());

                    objectOutputStream2.writeObject(requisicao);
                    System.out.println("Mensagem enviada para node 2: " + requisicao);

                    Integer mensagemRecebida = (Integer) objectInputStream2.readObject();
                    System.out.println("Mensagem recebida de node 2: " + mensagemRecebida);

                    objectOutputStream3.writeObject(mensagemRecebida);
                    System.out.println("Mensagem enviada para o cliente: " + mensagemRecebida);

                } else {
                    System.out.println("Node 2 não está disponível");
                }
                socket2.close();

            }

        }
    }

    //Método que realiza a operação 2
    private static Integer operacao2(Integer x, Integer y){
        return  2 * x / y;
    }
}
