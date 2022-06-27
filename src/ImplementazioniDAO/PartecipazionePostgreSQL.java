package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.PartecipazioneDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartecipazionePostgreSQL implements PartecipazioneDAO {

    private Connection conn;

    public PartecipazionePostgreSQL() throws SQLException{
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe EmailPostgreSQL");
            e.printStackTrace();
        }
    }
    @Override
    public void entrataInGruppo(int id, String nomeGruppo) throws SQLException {

        if(!nomeGruppo.isEmpty()) {
            conn = Connessione.getInstance().getConnection();
            try {
                PreparedStatement inserisciPersonaInGruppo = conn.prepareStatement("INSERT INTO Partecipazione (idcontatto, nomegruppo) VALUES ('" + id + "','" + nomeGruppo + "');");
                inserisciPersonaInGruppo.executeUpdate();
            } catch (
                    SQLException e) {
                System.out.println("ECCEZIONE::Riga 105 Classe CreaContattoPostgreSQL");
                e.printStackTrace();
            }

            conn.close();
        }

    }

    public void rimuoviPartecipazione(int id, String nomeGruppo) throws SQLException {
        PreparedStatement rimuoviPartecipazione = conn.prepareStatement("DELETE FROM Partecipazione WHERE idcontatto = '"+id+"' AND nomegruppo = '"+nomeGruppo+"'");

    }
    public void aggiornaPartecipazione(int id, String nomeGruppo) throws SQLException {
        PreparedStatement rimuoviPartecipazione = conn.prepareStatement("DELETE FROM Partecipazione WHERE idcontatto = '"+id+"' AND nomegruppo = '"+nomeGruppo+"'");
        PreparedStatement aggiornaPartecipazione = conn.prepareStatement("INSERT INTO Partecipazione(idcontatto,nomegruppo) VALUES ( nomegruppo = '"+nomeGruppo+"' , idcontatto = '"+id+"')");

    }
}
