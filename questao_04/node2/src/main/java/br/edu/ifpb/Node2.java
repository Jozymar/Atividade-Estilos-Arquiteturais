package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Node2 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8081);

        //Criando socket
        Socket socket = serverSocket.accept();

        DaoPostgres daoPostgres = new DaoPostgres();

        DaoMysql daoMysql = new DaoMysql();

        long tempoInicial = 0;
        long tempoFinal = 0;

        for (int i = 0; i < 1000; i ++) {

            if (i == 0) {
                tempoInicial = System.currentTimeMillis();
            }

            if (i == 999) {
                tempoFinal = System.currentTimeMillis();
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            User user = (User) objectInputStream.readObject();
            System.out.println("Mensagem recebida de node1: " + user);

            daoPostgres.insert(user);

            daoMysql.insert(user);

        }

        System.out.println("Tempo total: " + ((tempoFinal - tempoInicial)/1000) + " segundos.");

        socket.close();
    }
}
