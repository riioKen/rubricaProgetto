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


        String queryCercaInfoContatto = ("SELECT * FROM Contatto WHERE nome = '"+nomeSQL+"' AND cognome = '"+cognomeSQL+"'");
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
        }
        return contatto;
    }

}
