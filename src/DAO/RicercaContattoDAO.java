package DAO;

import Classi.Contatti;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RicercaContattoDAO {
    public ArrayList<Contatti> ricercaNome(String nome) throws SQLException;
    public ArrayList<Contatti> ricercaCellulare(String cellulare) throws SQLException;
    public ArrayList<Contatti> ricercaNickname(String Nickname);
    public ArrayList<Contatti> ricercaEmail(String email) throws SQLException;

}
