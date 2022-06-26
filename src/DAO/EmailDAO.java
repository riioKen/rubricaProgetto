package DAO;

import java.sql.SQLException;

public interface EmailDAO {
    void inserimentoEmail(int id, String email) throws SQLException;
    void rimozioneEmail(String id, String email) throws SQLException;
}
