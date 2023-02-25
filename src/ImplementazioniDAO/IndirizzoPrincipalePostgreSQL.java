package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.IndirizzoPrincipaleDAO;
import Model.Indirizzo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndirizzoPrincipalePostgreSQL implements IndirizzoPrincipaleDAO {

    private Connection conn;

    public IndirizzoPrincipalePostgreSQL() throws SQLException {
        try {
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe IndirizzoPrincipale PostgreSQL");
            e.printStackTrace();
        }
    }


    @Override
    public void inserisciIndirizzoPrincipale(int id, Indirizzo indirizzoPrincipale) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        try {

            String inserisciIndirizzo = "INSERT INTO IndirizzoPrincipale(via, civico, citta, cap, nazione, idcontatto) " +
                    "VALUES ('" + indirizzoPrincipale.getVia() + "', '" + indirizzoPrincipale.getCivico() + "', '" + indirizzoPrincipale.getCitta() + "', '" + indirizzoPrincipale.getCap() + "', '" + indirizzoPrincipale.getNazione() + "', '" + id + "')";
            PreparedStatement st = conn.prepareStatement(inserisciIndirizzo);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 42 Classe IndirizzoPrincipalePostgreSQL");
            e.printStackTrace();
        }

    }

    public Indirizzo estraiIndirizzoPrincipale(long id){

        String estraiIndirizzoPrincipale = "SELECT * FROM indirizzoPrincipale WHERE idContatto = '"+id+"'";
        Indirizzo indirizzo = new Indirizzo();

        try {
            conn = Connessione.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(estraiIndirizzoPrincipale);

            ResultSet rsIndirizzi = st.executeQuery();
            while(rsIndirizzi.next()) {
                indirizzo.setVia(rsIndirizzi.getString("via"));
                indirizzo.setCivico(rsIndirizzi.getString("civico"));
                indirizzo.setCap(rsIndirizzi.getString("cap"));
                indirizzo.setCitta(rsIndirizzi.getString("citta"));
                indirizzo.setNazione(rsIndirizzi.getString("nazione"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return indirizzo;
    }

    public void aggiornaIndirizzoPrincipale(long id, Indirizzo indirizzoNew){

        try {
            conn = Connessione.getInstance().getConnection();
            String aggiornaIndirizzoPrincipale = "UPDATE IndirizzoPrincipale " +
                    "SET via = '"+indirizzoNew.getVia()+"', civico = '"+indirizzoNew.getCivico()+"', citta = '"+indirizzoNew.getCitta()+"', nazione = '"+indirizzoNew.getNazione()+"', " +
                    "cap = '"+indirizzoNew.getCap()+"' WHERE idcontatto = '"+id+"'  ";
            PreparedStatement st = conn.prepareStatement(aggiornaIndirizzoPrincipale);
            st.execute();
            }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
