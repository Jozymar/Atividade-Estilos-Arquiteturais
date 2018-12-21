package br.edu.ifpb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoPostgres implements IUserDao{

    private Connection connection;

    public DaoPostgres() {
        try {
            this.connection =  ConFactory.getConnectionPostgres();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Método para inserir os dados no banco Postgres
    @Override
    public boolean insert(User user) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO tb_user (code, name) VALUES (?,?)");

        stmt.setInt(1, user.getCode());
        stmt.setString(2, user.getName());

        boolean retorno = stmt.executeUpdate() > 0;
        return retorno;
    }
}
