package ImplementazioniDAO;

import Classi.Contatti;
import ConnessioneDB.Connessione;
import DAO.AggiornamentoContattoDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
    public void aggiornaContatto(Contatti contatto, Contatti contattoNew, ArrayList<String> indirizzoVecchio, ArrayList<String> arrayTxtIndirizzo, ArrayList<String> emailVecchio, ArrayList<String> arrayTxtEmail) throws SQLException {

        conn = Connessione.getInstance().getConnection();

        try {
            PreparedStatement aggiornaContatto = conn.prepareStatement("UPDATE Contatto SET nome = '" + contattoNew.getNome() + "' , cognome = '" + contattoNew.getCognome() + "' , foto = '" + contattoNew.getFoto() + "' WHERE id = '" + contatto.getId() + "'");
            PreparedStatement aggiornaEmail = conn.prepareStatement("UPDATE Email SET email = '" + contattoNew.getEmail() + "' WHERE idcontatto = '" + contatto.getId() + "' AND email = '"+contatto.getEmail()+"'");

            PreparedStatement aggiornaNumeroCellulare = conn.prepareStatement("UPDATE numerocellulare SET cellulare = '" + contattoNew.getCellulare() + "' WHERE idcontatto = '" + contatto.getId() + "' AND cellulare = '" + contatto.getCellulare() + "'");
            PreparedStatement aggiornaFisso = conn.prepareStatement("UPDATE numerofisso SET fisso = '" + contattoNew.getFisso() + "' WHERE idcontatto = '" + contatto.getId() + "' AND fisso = '"+contatto.getFisso()+"'");
            aggiornaContatto.executeUpdate();
            aggiornaEmail.executeUpdate();

            aggiornaNumeroCellulare.executeUpdate();
            aggiornaFisso.executeUpdate();

            splittaIndirizzo(contatto.getIndirizzo(), contatto.getId());
            aggiornaEmailSecondarie(arrayTxtEmail, contatto.getId(), emailVecchio);
            splittaIndirizzoSecondario(arrayTxtIndirizzo, contatto.getId(),indirizzoVecchio);

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

    public void aggiornaEmailSecondarie(ArrayList<String> arrayTxtEmail, int id, ArrayList<String> emailVecchie) throws SQLException {
        String email;

        try {
            if (arrayTxtEmail != null) {
                for (int i = 0; i < arrayTxtEmail.size(); i++) {
                    email = arrayTxtEmail.get(i);
                    if (!email.isBlank()) {
                        PreparedStatement inserisciContattoEmail = conn.prepareStatement("UPDATE Email SET email = '" + email + "' WHERE idcontatto = '" + id + "' AND email = '"+emailVecchie.get(i)+"'");
                        inserisciContattoEmail.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 100 Classe CreaContattoPostgreSQL");
            e.printStackTrace();
        }
    }

    public void splittaIndirizzoSecondario(ArrayList<String> arrayTxtIndirizzo, int id, ArrayList<String> indirizziVecchi) throws SQLException {
        String via, civico, cap, citta, nazione;
        String viaVecchia, civicoVecchia, capVecchia, cittaVecchia, nazioneVecchia;

        try {
            if (arrayTxtIndirizzo != null) {
                for (int i = 0; i < arrayTxtIndirizzo.size(); i++) {
                    if (arrayTxtIndirizzo.get(i) != null || !arrayTxtIndirizzo.get(i).isBlank()) {
                        System.out.println("stampa di quante volte entra nella i " + i);

                        via = arrayTxtIndirizzo.get(i).split("\\s*,\\s*")[0];
                        civico =arrayTxtIndirizzo.get(i).split("\\s*,\\s*")[1];
                        cap = arrayTxtIndirizzo.get(i).split("\\s*,\\s*")[2];
                        citta = arrayTxtIndirizzo.get(i).split("\\s*,\\s*")[3];
                        nazione = arrayTxtIndirizzo.get(i).split("\\s*,\\s*")[4];

                        viaVecchia = indirizziVecchi.get(i).split("\\s*,\\s*")[0];
                        civicoVecchia = indirizziVecchi.get(i).split("\\s*,\\s*")[1];
                        capVecchia = indirizziVecchi.get(i).split("\\s*,\\s*")[2];
                        cittaVecchia = indirizziVecchi.get(i).split("\\s*,\\s*")[3];
                        nazioneVecchia = indirizziVecchi.get(i).split("\\s*,\\s*")[4];

                        System.out.println(via + " " + viaVecchia);
                        if (!via.isBlank() && !civico.isBlank() && !cap.isBlank() && !citta.isBlank() && !nazione.isBlank()) {
                            PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("UPDATE IndirizzoSecondario SET via = '" + via + "' , civico =  '" + civico + "' , cap =  '" + cap + "' , citta = '" + citta + "' , nazione = '" + nazione + "' " +
                                                                                                      "WHERE idcontatto = '" + id + "' AND via = '"+viaVecchia+"' AND civico = '"+civicoVecchia+"' AND cap = '"+capVecchia+"' AND citta = '"+cittaVecchia+"' AND nazione = '"+nazioneVecchia+"'");
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
