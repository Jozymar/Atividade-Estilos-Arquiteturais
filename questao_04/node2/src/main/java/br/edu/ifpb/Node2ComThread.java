package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Node2ComThread {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Criando servidor
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("Aguardando inserções...");

        //Criando socket
        Socket socket = serverSocket.accept();

        //Instancia o dao do Postgres
        DaoPostgres daoPostgres = new DaoPostgres();

        //Instancia o dao do Mysql
        DaoMysql daoMysql = new DaoMysql();

        //Variáveis que armazenam o tempo das inserções
        long tempoInicial = 0;
        long tempoFinal = 0;

        for (int i = 0; i < 1000; i ++) {

            if (i == 0) {
                tempoInicial = System.currentTimeMillis();
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            final User user = (User) objectInputStream.readObject();

            //Thread para inserir os dados nos bancos
            Thread thread = new Thread(() -> {
               try {
                   daoPostgres.insert(user);
                   daoMysql.insert(user);
               } catch (SQLException e) {
                   e.printStackTrace();
               }
            });

            thread.start();

            if (i == 999) {
                tempoFinal = System.currentTimeMillis();
            }

        }

        System.out.println("Inserções finalizadas.");
        System.out.println("Tempo total: " + ((tempoFinal - tempoInicial)/1000) + " segundos.");

        socket.close();
    }
}
