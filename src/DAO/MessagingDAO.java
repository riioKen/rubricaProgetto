package DAO;

import Model.Messaging;

import java.sql.SQLException;

public interface MessagingDAO {
    Messaging ricercaProvider(int id, String provider) throws SQLException;
    void inserimentoAccountMessaging(int id, int email, Messaging messaging) throws SQLException;
}
