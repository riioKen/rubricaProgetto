package ImplementazioniDAO;

import Classi.Messaging;
import ConnessioneDB.Connessione;
import DAO.MessagingDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessagingPostgreSQL implements MessagingDAO {
    private Connection conn;
    public MessagingPostgreSQL() throws SQLException{
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe ContattoPostgreSQL");
            e.printStackTrace();
        }
    }


    @Override
    public Messaging ricercaProvider(int id, String provider) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String ricercaProvider = "SELECT * FROM Messaging WHERE idcontatto = '"+id+"' AND providermessaggi = '"+provider+"'";

        PreparedStatement st = conn.prepareStatement();

    }
}
