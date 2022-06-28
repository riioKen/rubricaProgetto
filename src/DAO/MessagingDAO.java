package DAO;

import Classi.Messaging;

import java.sql.SQLException;

public interface MessagingDAO {
    Messaging ricercaProvider(int id, String provider) throws SQLException;
    void inserimentoAccountMessaging(int id, String email, Messaging messaging) throws SQLException;
}
