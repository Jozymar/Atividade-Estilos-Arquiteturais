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

        //Variáveis que armazenam o tempo das inserções
        long tempoInicial = 0;
        long tempoFinal = 0;

        //Para realizar os testes altere o valor de 'i'
        //(i < 100, i < 1000)
        for (int i = 0; i < 1000; i ++) {

            if (i == 0) {
                tempoInicial = System.currentTimeMillis();
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            User user = (User) objectInputStream.readObject();

            daoPostgres.insert(user);

            daoMysql.insert(user);

            //Para realizar os testes altere o valor de 'i'
            //(i == 99, i == 999)
            if (i == 999) {
                tempoFinal = System.currentTimeMillis();

            }

        }

        System.out.println("Inserções finalizadas.");
        System.out.println("Tempo total: " + ((tempoFinal - tempoInicial)/1000) + " segundos.");

        socket.close();
    }
}
