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
        System.out.println("Aguardando inserções...");

        //Criando socket
        Socket socket = serverSocket.accept();

        //Instancia o dao do Postgres
        DaoPostgres daoPostgres = new DaoPostgres();

        //Instancia o dao do Mysql
        DaoMysql daoMysql = new DaoMysql();

        long tempoInicial = System.currentTimeMillis();

        for (int i = 0; i < 1000; i ++) {

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            User user = (User) objectInputStream.readObject();

            daoPostgres.insert(user);

            daoMysql.insert(user);

        }

        long tempoFinal = System.currentTimeMillis();

        System.out.println("Inserções finalizadas.");
        System.out.println("Tempo total: " + ((tempoFinal - tempoInicial)/1000) + " segundos.");

        socket.close();
    }
}
