package DAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CreaContattoDAO {

    void creaContatto(String nome, String cellulare, String cognome, String fisso, String email, String indirizzo, ArrayList<JTextField> listaIndirizzi, ArrayList<JTextField> listaEmail) throws SQLException;

}
