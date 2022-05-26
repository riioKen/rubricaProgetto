package DAO;

import java.sql.SQLException;

public interface EliminaContattoDAO {

    void eliminaContatto(String cellulare) throws SQLException;
}
