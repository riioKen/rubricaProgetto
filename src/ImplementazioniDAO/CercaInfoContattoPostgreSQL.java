package ImplementazioniDAO;

import Classi.Contatti;
import ConnessioneDB.Connessione;
import DAO.CercaInfoContattoDAO;

import java.sql.*;

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
    public Contatti cercaInfoContatti(String dati) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        Contatti contatto = new Contatti();

        String queryCercaInfoContatto = ("SELECT * FROM Contatto as c JOIN Email as e ON c.id = e.idcontatto " +
                                         "JOIN IndirizzoPrincipale as i ON c.id = i.idcontatto JOIN NumeroCellulare as n " +
                                         " ON c.id = n.idcontatto JOIN NumeroFisso as f ON c.id = f.idcontatto WHERE n.cellulare = '"+dati+"'");
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
        conn.close();
        return contatto;
    }

}
