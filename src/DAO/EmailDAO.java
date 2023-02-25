package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmailDAO {
    int inserimentoEmail(int id, String email) throws SQLException;
    boolean controlloDuplicatoEmail(String email) throws SQLException;

    void editEmail(ArrayList<String> listaEemailOld, ArrayList<String> listaTxtEmailNew);
}
