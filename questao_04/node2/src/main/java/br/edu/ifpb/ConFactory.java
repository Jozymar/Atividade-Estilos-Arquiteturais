package br.edu.ifpb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {

    public static Connection getConnectionPostgres() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/sd";
        String usuario = "postgres";
        String senha = "postgres";

        return DriverManager.getConnection(url, usuario, senha);
    }

    public static Connection getConnectionMysql() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/sd";
        String usuario = "root";
        String senha = "deus";

        return DriverManager.getConnection(url, usuario, senha);
    }
}
