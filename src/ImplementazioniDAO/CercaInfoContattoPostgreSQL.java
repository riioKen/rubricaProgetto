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
    public Contatti cercaInfoContatti(String ncellulare, ArrayList<String> indirizzoSecondario, ArrayList<String> emailSecondario) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        Contatti contatto = new Contatti();

        String queryCercaInfoContatto = ("SELECT * FROM Contatto as c JOIN Email as e ON c.id = e.idcontatto " +
                                         "JOIN IndirizzoPrincipale as i ON c.id = i.idcontatto JOIN NumeroCellulare as n " +
                                         " ON c.id = n.idcontatto JOIN NumeroFisso as f ON c.id = f.idcontatto WHERE n.cellulare = '"+ncellulare+"'");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryCercaInfoContatto);

        while (rs.next()) {

            String nome = rs.getString("nome");
            contatto.setNome(nome);
            String cognome = rs.getString("cognome");
            contatto.setCognome(cognome);
            String cellulare = rs.getString("cellulare");
            contatto.setCellulare(cellulare);
            String fisso = rs.getString("fisso");
            contatto.setFisso(fisso);
            String email = rs.getString("email");
            contatto.setEmail(email);
            String indirizzo = rs.getString("via" );
            String civico = rs.getString("civico");
            String cap = rs.getString("cap");
            String citta = rs.getString("citta");
            String nazione = rs.getString("nazione");
            contatto.setIndirizzo(indirizzo+", "+civico+", "+cap+", "+citta+", "+nazione);

        }

        estraiInformazioniSecondarie(ncellulare, indirizzoSecondario, emailSecondario);
        conn.close();
        return contatto;
    }

    public void estraiInformazioniSecondarie(String ncellulare, ArrayList<String> indirizzoSecondario, ArrayList<String> emailSecondario) throws SQLException {

        String queryCercaInfoContatto = ("SELECT * FROM NumeroCellulare as c JOIN IndirizzoSecondario as i ON i.idcontatto = c.idcontatto " +
                                         "JOIN emailsecondario as es ON es.idcontatto = c.idcontatto WHERE c.cellulare = '"+ncellulare+"'");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryCercaInfoContatto);
        while(rs.next()) {

            String indirizzo = rs.getString("via");
            String civico = rs.getString("civico");
            String cap = rs.getString("cap");
            String citta = rs.getString("citta");
            String nazione = rs.getString("nazione");
            indirizzoSecondario.add(indirizzo + ", " + civico + ", " + cap + ", " + citta + ", " + nazione);
            emailSecondario.add(rs.getString("email"));

            System.out.println("Controllo riga 78 classe CercaInfoDAO\nindirizzi secondario"+" "+ indirizzoSecondario.size()+" email secondario"+" "+ emailSecondario.size());
        }
    }


}
