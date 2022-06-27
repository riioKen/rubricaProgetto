package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.AggiornamentoContattoDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AggiornamentoContattoPostreSQL implements AggiornamentoContattoDAO {

    private Connection conn;

    public AggiornamentoContattoPostreSQL() {
        try {
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void aggiornaContatto(int id, String nome, String cellulare, String cognome, String fisso, String email, String indirizzo, String foto, String nomeGruppo, JTextField[] listaIndirizzi, JTextField[] listaEmail) throws SQLException {

        conn = Connessione.getInstance().getConnection();

        try {
            PreparedStatement aggiornaContatto = conn.prepareStatement("UPDATE Contatto SET nome = '" + nome + "' , cognome = '" + cognome + "' , foto = '" + foto + "' WHERE id = '" + id + "'");
            PreparedStatement aggiornaEmail = conn.prepareStatement("UPDATE Email SET email = '" + email + "' WHERE idcontatto = '" + id + "'");


            PreparedStatement rimuoviPartecipazione = conn.prepareStatement("DELETE FROM Partecipazione WHERE idcontatto = '" + id + "' AND nomegruppo = '" + nomeGruppo + "'");
            PreparedStatement aggiungiPartecipazione = conn.prepareStatement("INSERT INTO Partecipazione(idcontatto,nomegruppo) VALUES (idcontatto = '" + id + "' , nomegruppo = '" + nomeGruppo + "')");
            PreparedStatement aggiornaNumeroCellulare = conn.prepareStatement("UPDATE numerocellulare SET cellulare = '" + cellulare + "' WHERE idcontatto = '" + id + "'");
            PreparedStatement aggiornaFisso = conn.prepareStatement("UPDATE numerofisso SET fisso = '" + fisso + "' WHERE idcontatto = '" + id + "'");
            aggiornaContatto.executeUpdate();
            aggiornaEmail.executeUpdate();
            rimuoviPartecipazione.executeUpdate();
            aggiungiPartecipazione.executeUpdate();
            aggiornaNumeroCellulare.executeUpdate();
            aggiornaFisso.executeUpdate();
            splittaIndirizzo(indirizzo, id);
            aggiornaEmailSecondarie(listaEmail, id);
            splittaIndirizzoSecondario(listaIndirizzi, id);

        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 51 Classe AggiornamentoContattoPostgreSQL");
            e.printStackTrace();
        }
    }

    public void splittaIndirizzo(String indirizzo, int id) throws SQLException {
        try {
            String via, civico, cap, citta, nazione;
            String[] divisione = indirizzo.split("\\s*,\\s*");
            via = divisione[0];
            civico = divisione[1];
            cap = divisione[2];
            citta = divisione[3];
            nazione = divisione[4];

            PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("UPDATE IndirizzoPrincipale SET via = '" + via + "' , civico = '" + civico + "' , cap = '" + cap + "' , citta = '" + citta + "' , nazione ='" + nazione + "' WHERE idcontatto = '" + id + "'");
            inserisciContattoIndirizzo.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 80 Classe AggiornamentoContattoPostgreSQL");
            e.printStackTrace();
        }

    }

    public void aggiornaEmailSecondarie(JTextField[] listaEmail, int id) throws SQLException {
        String email;
        try {
            if (listaEmail != null) {
                for (int i = 0; i < listaEmail.length; i++) {
                    email = listaEmail[i].getText();
                    if (!email.isBlank()) {
                        PreparedStatement inserisciContattoEmail = conn.prepareStatement("UPDATE EmailSecondario SET email = '" + email + "' WHERE idcontatto = '" + id + "'");
                        inserisciContattoEmail.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 100 Classe CreaContattoPostgreSQL");
            e.printStackTrace();
        }
    }

    public void splittaIndirizzoSecondario(JTextField[] listaIndirizzo, int id) throws SQLException {
        String via, civico, cap, citta, nazione;

        try {
            if (listaIndirizzo != null) {
                for (int i = 0; i < listaIndirizzo.length; i++) {
                    if (listaIndirizzo[i].getText() != null || !listaIndirizzo[i].getText().isBlank()) {
                        System.out.println("stampa di quante volte entra nella i " + i);

                        via = listaIndirizzo[i].getText().split("\\s*,\\s*")[0];
                        civico = listaIndirizzo[i].getText().split("\\s*,\\s*")[1];
                        cap = listaIndirizzo[i].getText().split("\\s*,\\s*")[2];
                        citta = listaIndirizzo[i].getText().split("\\s*,\\s*")[3];
                        nazione = listaIndirizzo[i].getText().split("\\s*,\\s*")[4];
                        System.out.println(via + " " + citta);
                        if (!via.isBlank() && !civico.isBlank() && !cap.isBlank() && !citta.isBlank() && !nazione.isBlank()) {
                            PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("UPDATE IndirizzoSecondario SET via = '" + via + "' , civico =  '" + civico + "' , cap =  '" + cap + "' , citta = '" + citta + "' , nazione = '" + nazione + "' WHERE idcontatto = '" + id + "'");
                            inserisciContattoIndirizzo.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 129 Classe CreaContattoPostgreSQL");
            e.printStackTrace();
        }
    }


}
