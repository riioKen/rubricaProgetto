package ImplementazioniDAO;

import Classi.Messaging;
import ConnessioneDB.Connessione;
import DAO.MessagingDAO;

import java.sql.*;

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

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(ricercaProvider);
        Messaging messaging = new Messaging();

        while(rs.next()){
            messaging.setNickname(rs.getString("nickname"));
            messaging.setProviderMessaggi(rs.getString("providermessaggi"));
            messaging.setEmail(rs.getString("email"));
            messaging.setMessaggioBenvenuto(rs.getString("messaggiobenvenuto"));
            messaging.setIdcontatto(rs.getInt("idcontatto"));
        }
        return messaging;
    }

    @Override
    public void inserimentoAccountMessaging(int id, String email, Messaging messaging) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String inserisciDati = "INSERT INTO Messaging(nickname, providermessaggi, messaggiobenvenuto, idcontatto, email)" +
                               "VALUES('"+messaging.getNickname()+"'," +
                               "'"+messaging.getProviderMessaggi()+"', '"+messaging.getMessaggioBenvenuto()+"', " +
                               "'"+id+"', '"+email+"')";
        Statement st = conn.createStatement();
        st.executeUpdate(inserisciDati);
    }
}
