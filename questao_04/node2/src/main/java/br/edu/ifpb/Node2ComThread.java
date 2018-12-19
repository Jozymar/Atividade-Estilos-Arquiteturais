package br.edu.ifpb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Node2ComThread {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

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
            final User user = (User) objectInputStream.readObject();

            //Thread para inserir dados no Postgres
               Thread pg = new Thread(() -> {
                   try {
                       daoPostgres.insert(user);
                   } catch (SQLException e) {
                       e.printStackTrace();
                   } catch (ClassNotFoundException e) {
                       e.printStackTrace();
                   }
               });

               pg.start();

               pg.join();

            //Thread para inserir dados no Mysql
            Thread my = new Thread(() -> {
                try {
                    daoMysql.insert(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });

            my.start();

        }

        long tempoFinal = System.currentTimeMillis();

        System.out.println("Inserções finalizadas.");
        System.out.println("Tempo total: " + ((tempoFinal - tempoInicial)/1000) + " segundos.");

        socket.close();
    }
}
