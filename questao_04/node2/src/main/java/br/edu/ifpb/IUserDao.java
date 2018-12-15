package br.edu.ifpb;

import java.sql.SQLException;

public interface IUserDao {

    public boolean insert(User user) throws SQLException, ClassNotFoundException;
}
