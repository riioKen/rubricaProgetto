package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.IndirizzoSecondarioDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class IndirizzoSecondarioPostgreSQL implements IndirizzoSecondarioDAO {

    private Connection conn;

    public IndirizzoSecondarioPostgreSQL(){
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void inserisciIndirizzoSecondario(int id, ArrayList<JTextField> listaIndirizzo) throws SQLException {
        String via, civico, cap, citta, nazione;
        conn = Connessione.getInstance().getConnection();
        try {
            if (listaIndirizzo != null) {
                for (int i = 0; i < listaIndirizzo.size(); i++) {
                    if (listaIndirizzo.get(i).getText() != null || !listaIndirizzo.get(i).getText().isBlank()) {
                        System.out.println("stampa di quante volte entra nella i " + i);

                        via = listaIndirizzo.get(i).getText().split("\\s*,\\s*")[0];
                        civico = listaIndirizzo.get(i).getText().split("\\s*,\\s*")[1];
                        cap = listaIndirizzo.get(i).getText().split("\\s*,\\s*")[2];
                        citta = listaIndirizzo.get(i).getText().split("\\s*,\\s*")[3];
                        nazione = listaIndirizzo.get(i).getText().split("\\s*,\\s*")[4];
                        System.out.println(via + " " + citta);
                        if (!via.isBlank() && !civico.isBlank() && !cap.isBlank() && !citta.isBlank() && !nazione.isBlank()) {
                            PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("Insert into IndirizzoSecondario(idcontatto, via, civico, cap, citta, nazione) VALUES ('" + id + "','" + via + "', '" + civico + "', '" + cap + "', '" + citta + "','" + nazione + "');");
                            inserisciContattoIndirizzo.executeUpdate();
                        }
                    }
                }
            }
        }
        catch (SQLException e){
            System.out.println("ECCEZIONE::Riga 145 Classe CreaContattoPostgreSQL");
            e.printStackTrace();
        }
    }

    @Override
    public void rimuoviIndirizzoSecondario(long id) throws SQLException {
        conn = Connessione.getInstance().getConnection();

        try{
            PreparedStatement eliminaIndirizzo = conn.prepareStatement("DELETE FROM IndirizzoSecondario WHERE idindirizzo = '" + id + "'");
            eliminaIndirizzo.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
