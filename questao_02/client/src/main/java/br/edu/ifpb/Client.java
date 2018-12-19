package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private static List<Node> nos = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando os nodes
        Node node1 = new Node(new InetSocketAddress("localhost",8081), "node1", false);
        Node node2 = new Node(new InetSocketAddress("localhost",8082), "node2", true);
        Node node3 = new Node(new InetSocketAddress("localhost",8083), "node3", false);

        // Nodes são adicionados a lista para verificar nodes não réplica
        nos.add(node1);
        nos.add(node2);
        nos.add(node3);

        //Requisições que serão enviadas
        Requisicao requisicao = new Requisicao("op1",10, 20);
//        Requisicao requisicao = new Requisicao("op2",5, 10);

        //Números gerados para representar a execução aleatória dos nodes
        //(Node1 = 1, Node2 = 2 e Node3 = 3)
        Integer enviar = 1 + (int) (Math.random() * 3);

        System.out.println("Requisição solicitada: " + requisicao);

        //Variáveis criadas para verificar a disponibilidade dos nodes
        boolean node1Disponivel;
        boolean node2Disponivel;
        boolean node3Disponivel;

        //Criando socket geral
        Socket socket = new Socket();

        if (enviar.equals(1)) {

            //Verifica se Node 1 está disponível
            try {
                socket.connect(node1.getEndereco());
                node1Disponivel = true;

            }catch (IOException e) {
                node1Disponivel = false;
            }

            if (node1Disponivel) {

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                System.out.println("Mensagem enviada: " + requisicao);
                objectOutputStream.writeObject(requisicao);

                Integer mensagemRecebida = (Integer) objectInputStream.readObject();
                System.out.println("Mensagem recebida de node 1: " + mensagemRecebida);

            } else {

                Socket socket3 = new Socket();

                socket3.connect(buscarNoNaoreplica(node1.getNome()).get(0).getEndereco());

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket3.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket3.getInputStream());

                System.out.println("Mensagem enviada: " + requisicao);
                objectOutputStream.writeObject(requisicao);

                try {
                    Integer mensagemRecebida = (Integer) objectInputStream.readObject();
                    System.out.println("Mensagem recebida de node 3: " + mensagemRecebida);
                } catch (Exception e) {
                    System.out.println("Nenhuma mensagem recebida!");
                    System.exit(0);
                }

            }
        } else if (enviar.equals(2)) {

            //Verifica se Node 2 está disponível
            try {
                socket.connect(node2.getEndereco());
                node2Disponivel = true;

            }catch (IOException e) {
                node2Disponivel = false;
            }

            if (node2Disponivel) {

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                System.out.println("Mensagem enviada: " + requisicao);
                objectOutputStream.writeObject(requisicao);

                Integer mensagemRecebida = (Integer) objectInputStream.readObject();
                System.out.println("Mensagem recebida de node 2: " + mensagemRecebida);

            } else {

                Socket socket1 = new Socket();

                Boolean no1Disponivel;

                try {
                    socket1.connect(buscarNoNaoreplica(node2.getNome()).get(0).getEndereco());
                    no1Disponivel = true;

                }catch (IOException e) {
                    no1Disponivel = false;
                }

                if (no1Disponivel) {

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket1.getInputStream());

                    System.out.println("Mensagem enviada: " + requisicao);
                    objectOutputStream.writeObject(requisicao);

                    Integer mensagemRecebida = (Integer) objectInputStream.readObject();
                    System.out.println("Mensagem recebida: " + mensagemRecebida);

                } else {

                    Socket socket3 = new Socket();

                    socket3.connect(buscarNoNaoreplica(node2.getNome()).get(1).getEndereco());

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket3.getOutputStream());
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket3.getInputStream());

                    System.out.println("Mensagem enviada: " + requisicao);
                    objectOutputStream.writeObject(requisicao);

                    try {
                        Integer mensagemRecebida = (Integer) objectInputStream.readObject();
                        System.out.println("Mensagem recebida: " + mensagemRecebida);
                    } catch (Exception e) {
                        System.out.println("Nenhuma mensagem recebida!");
                        System.exit(0);
                    }

                }

            }
        } else {

            //Verifica se Node 3 está disponível
            try {
                socket.connect(node3.getEndereco());
                node3Disponivel = true;

            }catch (IOException e) {
                node3Disponivel = false;
            }

            if (node3Disponivel) {

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                System.out.println("Mensagem enviada: " + requisicao);
                objectOutputStream.writeObject(requisicao);

                try {
                    Integer mensagemRecebida = (Integer) objectInputStream.readObject();
                    System.out.println("Mensagem recebida de node 3: " + mensagemRecebida);
                } catch (Exception e) {
                    System.out.println("Nenhuma mensagem recebida!");
                    System.exit(0);
                }

            } else {

                Socket socket1 = new Socket();

                socket1.connect(buscarNoNaoreplica(node3.getNome()).get(0).getEndereco());

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket1.getInputStream());

                System.out.println("Mensagem enviada: " + requisicao);
                objectOutputStream.writeObject(requisicao);

                Integer mensagemRecebida = (Integer) objectInputStream.readObject();
                System.out.println("Mensagem recebida de node 1: " + mensagemRecebida);

            }
        }

        socket.close();

    }

    // Método que busca um node não réplica
    private static List<Node> buscarNoNaoreplica(String nome) {

        //Lista que recebe os nodes não réplica
        List<Node> noNaoReplica = new ArrayList<>();

        for(Node no : nos){
            if(!no.getNome().equals(nome) && !no.isReplica())
                noNaoReplica.add(no);
        }

        if(noNaoReplica.isEmpty()) {
            System.out.println("Não há nenhum nó não réplica disponível!");
        }

        return noNaoReplica;
    }
}
