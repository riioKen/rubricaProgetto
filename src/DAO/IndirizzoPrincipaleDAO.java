package DAO;

import Model.Indirizzo;

import java.sql.SQLException;

public interface IndirizzoPrincipaleDAO {
    void inserisciIndirizzoPrincipale(int id, Indirizzo indirizzoPrincipale) throws SQLException;
    Indirizzo estraiIndirizzoPrincipale(long id);

    public void aggiornaIndirizzoPrincipale(long id, Indirizzo indirizzoNew);

}
