package br.edu.ifpb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoMysql implements IUserDao{

    @Override
    public boolean insert(User user) throws SQLException, ClassNotFoundException {

        Connection con = ConFactory.getConnectionMysql();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO tb_user (code, name) VALUES (?,?)");

        stmt.setInt(1, user.getCode());
        stmt.setString(2, user.getName());

        boolean retorno = stmt.executeUpdate() > 0;

        con.close();
        return retorno;
    }
}
