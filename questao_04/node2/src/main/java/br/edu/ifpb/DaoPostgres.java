package br.edu.ifpb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoPostgres implements IUserDao{

    //Método para inserir os dados no banco Postgres
    @Override
    public boolean insert(User user) throws SQLException, ClassNotFoundException {

        Connection con = ConFactory.getConnectionPostgres();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO tb_user (code, name) VALUES (?,?)");

        stmt.setInt(1, user.getCode());
        stmt.setString(2, user.getName());

        boolean retorno = stmt.executeUpdate() > 0;

        con.close();
        return retorno;
    }
}
