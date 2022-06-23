package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.AggiornamentoContattoDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AggiornamentoContattoPostreSQL implements AggiornamentoContattoDAO {

    private Connection conn;

    public AggiornamentoContattoPostgreSQL(){
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void aggiornaContatto(String nome, String cellulare, String cognome, String fisso, String email, String indirizzo, String foto, String nomeGruppo, JTextField[] listaIndirizzi, JTextField[] listaEmail) throws SQLException {
        int id =0;
        conn = Connessione.getInstance().getConnection();

        try {
            PreparedStatement ricavaID = conn.prepareStatement("SELECT id FROM Contatto WHERE nome = '"+nome+"', cognome = '"+cognome+"', foto = '"+foto+"');");
            ricavaID.execute();
            ResultSet rs = ricavaID.getResultSet();
            rs.next();
            id = rs.getInt(1);

        }catch(SQLException e){
            System.out.println("ECCEZIONE::Riga 38 Classe CreaContattoPostgreSQL");
            e.printStackTrace();
        }

        try {
            PreparedStatement aggiornaContatto = conn.prepareStatement("UPDATE Contatto SET nome = '"+nome+"', cognome = '"+cognome+"', foto = '"+foto+"');");

            aggiornaContatto.executeUpdate();

        }catch(SQLException e){
            System.out.println("ECCEZIONE::Riga 38 Classe AggiornamentoContattoPostgreSQL");
            e.printStackTrace();
        }
    }
}
