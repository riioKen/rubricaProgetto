package DAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AggiornamentoContattoDAO {

    void aggiornaContatto(String nome, String cellulare, String cognome, String fisso, String email, String indirizzo, String foto, String nomeGruppo, JTextField[] listaIndirizzi, JTextField[] listaEmail) throws SQLException;

}
