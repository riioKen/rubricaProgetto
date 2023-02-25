package ImplementazioniDAO;

import Model.*;
import ConnessioneDB.Connessione;
import DAO.AggiornamentoContattoDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;


public class AggiornamentoContattoPostgreSQL implements AggiornamentoContattoDAO {

    private Connection conn;

    public AggiornamentoContattoPostgreSQL() {
        try {
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void aggiornaContatto(Contatti contatto, Indirizzo indirizzo, Contatti contattoNew, Indirizzo indirizzo_new, ArrayList<Indirizzo> indirizzoVecchio, ArrayList<String> arrayTxtIndirizzo) {
        try {
            conn = Connessione.getInstance().getConnection();
            PreparedStatement aggiornaContatto = conn.prepareStatement("UPDATE Contatto SET nome = '" + contattoNew.getNome() + "' , cognome = '" + contattoNew.getCognome() + "' , foto = '" + contattoNew.getFoto() + "' WHERE id = '" + contatto.getId() + "'");

            aggiornaContatto.executeUpdate();

            splittaIndirizzo(indirizzo_new, contatto.getId());
//            aggiornaEmailSecondarie(arrayTxtEmail, contatto.getId(), emailVecchio);
            splittaIndirizzoSecondario(arrayTxtIndirizzo, contatto.getId(),indirizzoVecchio);

        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 51 Classe AggiornamentoContattoPostgreSQL");
            e.printStackTrace();
        }
    }

    public void splittaIndirizzo(Indirizzo indirizzo, int id) {
        try {
            PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("UPDATE IndirizzoPrincipale SET via = '" + indirizzo.getVia() + "' , civico = '" + indirizzo.getCivico() + "' , cap = '" + indirizzo.getCap() + "' , citta = '" + indirizzo.getCitta() + "' , nazione ='" + indirizzo.getNazione() + "' WHERE idcontatto = '" + id + "'");
            inserisciContattoIndirizzo.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 80 Classe AggiornamentoContattoPostgreSQL");
            e.printStackTrace();
        }

    }

    public void aggiornaEmailSecondarie(ArrayList<String> arrayTxtEmail, int id, ArrayList<String> emailVecchie)  {
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

    public void splittaIndirizzoSecondario(ArrayList<String> arrayTxtIndirizzo, int id, ArrayList<Indirizzo> indirizziVecchi)  {
        String via, civico, cap, citta, nazione;
        String viaVecchia, civicoVecchia, capVecchia, cittaVecchia, nazioneVecchia;

        try {
            if (arrayTxtIndirizzo != null) {
                for (int i = 0; i < arrayTxtIndirizzo.size(); i++) {
                    if (arrayTxtIndirizzo.get(i) != null || !arrayTxtIndirizzo.get(i).isBlank()) {

                        via = arrayTxtIndirizzo.get(i).split("\\s*,\\s*")[0];
                        civico =arrayTxtIndirizzo.get(i).split("\\s*,\\s*")[1];
                        cap = arrayTxtIndirizzo.get(i).split("\\s*,\\s*")[2];
                        citta = arrayTxtIndirizzo.get(i).split("\\s*,\\s*")[3];
                        nazione = arrayTxtIndirizzo.get(i).split("\\s*,\\s*")[4];

//                        System.out.println(via + " " + viaVecchia);
                        if (indirizziVecchi.get(i) != null) {
                            PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("UPDATE IndirizzoSecondario SET via = '" + via + "' , civico =  '" + civico + "' , cap =  '" + cap + "' , citta = '" + citta + "' , nazione = '" + nazione + "' " +
                                                                                                      "WHERE idcontatto = '" + id + "' AND idIndirizzo = '"+indirizziVecchi.get(i).getId()+"'");
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
