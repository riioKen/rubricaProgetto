package DAO;

import Model.Contatti;
import Model.Indirizzo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ContattoDAO {
    int inserimentoContatto(String nome, String cognome, String foto) throws SQLException;
    ArrayList<Contatti> stampaContatti() throws SQLException;
    Contatti cercaInfoContatti(int id, ArrayList<Indirizzo> indirizzoSecondario, ArrayList<String> emailSecondario) throws SQLException;
    void eliminaContatto(int id) throws SQLException;

}
