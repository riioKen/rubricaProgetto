package DAO;

import java.sql.SQLException;

public interface CreaContattoDAO {

    public void creaContatto(String nome, String cellulare, String cognome, String fisso, String email, String indirizzo) throws SQLException;

}
