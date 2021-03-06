package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Node1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Gerando os números que serão enviados
        Random numeros = new Random();
        Integer numero1 = numeros.nextInt(101);
        Integer numero2 = numeros.nextInt(101);

        //Criando socket
        Socket socket1 = new Socket("localhost", 8081);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket1.getInputStream());

        Mensagem mensagem = new Mensagem(numero1, numero2);
        System.out.println("Mensagem enviada: " + mensagem);
        objectOutputStream.writeObject(mensagem);

        Double mensagemRecebida = (Double) objectInputStream.readObject();
        System.out.println("Mensagem recebida de node 2: " + mensagemRecebida);

        socket1.close();
    }
}
