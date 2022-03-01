package DAO;

import Classi.Contatti;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SchedaRicercheDAO {

    public ArrayList<Contatti> ricercaNome(String dato) throws SQLException;
    public ArrayList<Contatti> ricercaEmail(String dato) throws SQLException;
    public ArrayList<Contatti> ricercaCellulare(String dato) throws SQLException;
    public ArrayList<Contatti> ricercaNickname(String dato) throws SQLException;
}
