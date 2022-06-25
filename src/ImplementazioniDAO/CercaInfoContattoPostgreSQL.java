package ImplementazioniDAO;

import Classi.Contatti;
import ConnessioneDB.Connessione;
import DAO.CercaInfoContattoDAO;

import java.sql.*;
import java.util.ArrayList;

public class CercaInfoContattoPostgreSQL implements CercaInfoContattoDAO{

    private Connection conn;


    public CercaInfoContattoPostgreSQL(){
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //METODI
    @Override
    public Contatti cercaInfoContatti(int id, ArrayList<String> indirizzoSecondario, ArrayList<String> emailSecondario) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        Contatti contatto = new Contatti();

        String queryCercaInfoContatto = ("SELECT * FROM Contatto as c JOIN Email as e ON c.id = e.idcontatto " +
                                         "JOIN IndirizzoPrincipale as i ON c.id = i.idcontatto JOIN NumeroCellulare as n " +
                                         "ON c.id = n.idcontatto JOIN NumeroFisso as f ON c.id = f.idcontatto " +
                                         " JOIN Partecipazione as p ON c.id = p.idcontatto WHERE c.id = '"+id+"'");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryCercaInfoContatto);

        while (rs.next()) {
            contatto.setId(rs.getInt("id"));
            contatto.setNome(rs.getString("nome"));
            contatto.setCognome(rs.getString("cognome"));
            contatto.setCellulare(rs.getString("cellulare"));
            contatto.setFisso(rs.getString("fisso"));
            contatto.setEmail(rs.getString("email"));
            contatto.setFoto(rs.getString("foto"));
            contatto.setNomeGruppo(rs.getString("nomegruppo"));
            String indirizzo = rs.getString("via");
            String civico = rs.getString("civico");
            String cap = rs.getString("cap");
            String citta = rs.getString("citta");
            String nazione = rs.getString("nazione");
            contatto.setIndirizzo(indirizzo+", "+civico+", "+cap+", "+citta+", "+nazione);

        }

        estraiInformazioniSecondarie(id, indirizzoSecondario, emailSecondario);
        conn.close();
        return contatto;
    }

    public void estraiInformazioniSecondarie(int id, ArrayList<String> indirizzoSecondario, ArrayList<String> emailSecondario) throws SQLException {

        String queryCercaInfoContatto = ("SELECT * FROM Contatto as c JOIN IndirizzoSecondario as i ON i.idcontatto = c.id " +
                                         "WHERE c.id = '"+id+"'");
        Statement stIndirizziSecondari = conn.createStatement();
        ResultSet rsIndirizziSecondari = stIndirizziSecondari.executeQuery(queryCercaInfoContatto);
        while(rsIndirizziSecondari.next()) {
            String indirizzo = rsIndirizziSecondari.getString("via");
            String civico = rsIndirizziSecondari.getString("civico");
            String cap = rsIndirizziSecondari.getString("cap");
            String citta = rsIndirizziSecondari.getString("citta");
            String nazione = rsIndirizziSecondari.getString("nazione");
            indirizzoSecondario.add(indirizzo + ", " + civico + ", " + cap + ", " + citta + ", " + nazione);
        }


        String queryCercaEmailSecondarie = "SELECT * FROM Contatto as c JOIN emailsecondario as es ON es.idcontatto = c.id WHERE c.id = '"+id+"'";
        Statement stEmailSecondari = conn.createStatement();
        ResultSet rsEmailSecondari = stEmailSecondari.executeQuery(queryCercaEmailSecondarie);
        while(rsEmailSecondari.next()) {
            emailSecondario.add(rsEmailSecondari.getString("email"));
        }
    }



}
