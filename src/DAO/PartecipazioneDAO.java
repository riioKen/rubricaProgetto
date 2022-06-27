package DAO;

import java.sql.SQLException;

public interface PartecipazioneDAO {
    void entrataInGruppo(int id, String nomeGruppo) throws SQLException;
}
