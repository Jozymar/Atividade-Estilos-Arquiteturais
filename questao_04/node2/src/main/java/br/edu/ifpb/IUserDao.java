package br.edu.ifpb;

import java.sql.SQLException;

public interface IUserDao {

    boolean insert(User user) throws SQLException, ClassNotFoundException;
}
