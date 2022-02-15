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
        Contatti contatto = new Contatti();
        String nomeSQL;
        String cognomeSQL;
        String[] split = dati.split("\\s+");
        nomeSQL = split[0];
        cognomeSQL = split[1];
        int dim = split.length;
        if(dim == 3){
            cognomeSQL = split[1] +" "+ split[2];
        }
        else if(dim == 4){
            cognomeSQL = split[1] +" "+ split[2] +" "+split[3];
        }
        String queryCercaInfoContatto = ("SELECT * FROM Contatto as c JOIN Email as e ON c.cellulare = e.cellulare " +
                                         "JOIN Indirizzo as i ON c.cellulare = i.cellulare WHERE nome = '"+nomeSQL+"' AND cognome = '"+cognomeSQL+"'");
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
        return contatto;
    }

}
