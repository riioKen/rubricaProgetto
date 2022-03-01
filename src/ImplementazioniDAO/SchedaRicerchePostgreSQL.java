package ImplementazioniDAO;

import Classi.Contatti;
import ConnessioneDB.Connessione;
import DAO.SchedaRicercheDAO;
import Gui.SchedaRicerche;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SchedaRicerchePostgreSQL implements SchedaRicercheDAO {
    private Connection conn;

    public SchedaRicerchePostgreSQL(){

    }

    @Override
    public ArrayList<Contatti> ricercaNome(String dato) throws SQLException {
        try {
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Contatti> ricercaDB = new ArrayList<>();

        String queryRicercaPerNome = ("SELECT DISTINCT * FROM Contatto where nome LIKE '" + dato + "%'");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryRicercaPerNome);

        while (rs.next()) {

            Contatti contatto = new Contatti();
            String nome = rs.getString("nome");
            contatto.setNome(nome);
            String cognome = rs.getString("cognome");
            contatto.setCognome(cognome);
            String cellulare = rs.getString("cellulare");
            contatto.setCellulare(cellulare);
            String fisso = rs.getString("fisso");
            contatto.setFisso(fisso);

            ricercaDB.add(contatto);

        }

        conn.close();
        return ricercaDB;
    }

    @Override
    public ArrayList<Contatti> ricercaEmail(String dato) throws SQLException {
        try {
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Contatti> ricercaDB = new ArrayList<>();

        String queryRicercaPerNome = ("SELECT DISTINCT * FROM Email as e JOIN Contatto as c ON e.cellulare = c.cellulare where email LIKE '" + dato + "%'");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryRicercaPerNome);

        while (rs.next()) {

            Contatti contatto = new Contatti();
            String nome = rs.getString("nome");
            contatto.setNome(nome);
            String cognome = rs.getString("cognome");
            contatto.setCognome(cognome);
            String cellulare = rs.getString("cellulare");
            contatto.setCellulare(cellulare);
            String fisso = rs.getString("fisso");
            contatto.setFisso(fisso);

            ricercaDB.add(contatto);

        }

        conn.close();
        return ricercaDB;
    }

    @Override
    public ArrayList<Contatti> ricercaCellulare(String dato) throws SQLException {

        try {
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Contatti> ricercaDB = new ArrayList<>();

        String queryRicercaPerNome = ("SELECT DISTINCT * FROM Contatto where cellulare LIKE '" + dato + "%'");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryRicercaPerNome);

        while (rs.next()) {

            Contatti contatto = new Contatti();
            String nome = rs.getString("nome");
            contatto.setNome(nome);
            String cognome = rs.getString("cognome");
            contatto.setCognome(cognome);
            String cellulare = rs.getString("cellulare");
            contatto.setCellulare(cellulare);
            String fisso = rs.getString("fisso");
            contatto.setFisso(fisso);

            ricercaDB.add(contatto);

        }

        conn.close();
        return ricercaDB;
    }

    @Override
    public ArrayList<Contatti> ricercaNickname(String dato) throws SQLException {
        return null;
    }
}
