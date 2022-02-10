package ImplementazioniDAO;


import Classi.Contatti;
import ConnessioneDB.*;
import DAO.*;

import java.sql.*;
import java.util.ArrayList;

public class StampaContattoPostgreSQL implements StampaContattoDAO {

    ArrayList<Contatti> contattiDB = new ArrayList<>();

    private Connection conn;

    public StampaContattoPostgreSQL(){
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Contatti> stampaContatti() throws SQLException {

        String queryStampaContatti = "Select * from Contatto";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryStampaContatti);

        while (rs.next()){
            Contatti contatti =  new Contatti();

            String nome = rs.getString("nome");
            contatti.setNome(nome);
            String cognome = rs.getString("cognome");
            contatti.setCognome(cognome);
            String cellulare = rs.getString("cellulare");
            contatti.setCellulare(cellulare);
            String fisso = rs.getString("fisso");
            contatti.setFisso(fisso);

            contattiDB.add(contatti);
        }
        st.close();
        conn.close();
        return contattiDB;
    }

}

