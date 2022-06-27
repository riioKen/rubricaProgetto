package DAO;

import java.sql.SQLException;

public interface EmailDAO {
    void inserimentoEmail(int id, String email) throws SQLException;
    boolean controlloDuplicatoEmail(String email) throws SQLException;
}
