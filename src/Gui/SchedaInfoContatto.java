package Gui;

import Classi.Contatti;
import Controller.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import DAO.CercaInfoContattoDAO;
import DAO.CreaContattoDAO;
import DAO.EliminaContattoDAO;
import ImplementazioniDAO.CercaInfoContattoPostgreSQL;
import ImplementazioniDAO.CreaContattoPostgreSQL;
import ImplementazioniDAO.EliminaContattoPostgreSQL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SchedaInfoContatto {

    private JPanel SchedaInfoContattoPane;
    private JComboBox cbGruppo;
    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtCellulare;
    private JTextField txtFisso;
    private JTextField txtEmail;
    private JTextField txtIndirizzo;
    private JLabel lbNome;
    private JLabel lbCognome;
    private JLabel lbCellulare;
    private JLabel lbFisso;
    private JLabel lbEmail;
    private JLabel lbIndirizo;
    private JLabel lbGruppo;
    private JLabel lbWhatsApp;
    private JLabel lbRispostaWA;
    private JLabel lbTelegram;
    private JLabel lbRispostaTG;
    private JLabel lbTastoHome;
    private JButton btnAggiornaContatto;
    private JButton btnEliminaContatto;

    Controller control;

    CercaInfoContattoDAO cercaInfoContattoDAO = new CercaInfoContattoPostgreSQL();
    EliminaContattoDAO eliminaContattoDAO = new EliminaContattoPostgreSQL();
    CreaContattoDAO creaContattoDAO = new CreaContattoPostgreSQL();

    ArrayList<String> indirizzoSecondario = new ArrayList<>();
    ArrayList<String> emailSecondario = new ArrayList<>();
    String nCellulare;
    public  SchedaInfoContatto(Controller controller){
        control = controller;

        lbTastoHome();
        btnAggiornaContatto();
        btnEliminaContatto();

    }


    //METODI
    public void btnEliminaContatto(){
        btnEliminaContatto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    control.clickAudio();
                    eliminaContattoDAO.eliminaContatto(txtCellulare.getText());
                    control.getHomepage().stampaContatti();
                } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
                control.switchJPanelInView(control.getHomepage().getPaneBase());
            }
        });
    }
    public void btnAggiornaContatto(){
        btnAggiornaContatto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    aggiornamentoContatto();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public void svuotaCampiInfoContatto(){

            txtNome.setText("");
            txtCognome.setText("");
            txtCellulare.setText("");
            txtFisso.setText("");
            txtEmail.setText("");
            txtIndirizzo.setText("");

    }
    //IMPLEMENTARE MODIFICA DEL CONTATTO && CREAZIONE GRUPPO && AGGIUNTA AL GRUPPO DI UN CONTATTO && RIMOZIONE DAL GRUPPO DI UN CONTATTO
    public void lbTastoHome(){
        ImageIcon imgTastoHome = new ImageIcon("Immagini/imgRitornoHomeFreccia24px.png");
        ImageIcon imgTastoHomeGrande = new ImageIcon("Immagini/imgRitornoHomeFreccia32px.png");
        lbTastoHome.setIcon(imgTastoHome);
        lbTastoHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.rollOverAudio();
                    lbTastoHome.setIcon(imgTastoHome);
                    control.switchJPanelInView(control.getHomepage().getPaneBase());
                    control.getHomepage().stampaContatti();
                    control.getHomepage().getJScrollBarListaContatti().setBorder(BorderFactory.createTitledBorder("Lista dei Contatti"));
                } catch (SQLException | LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e){
                try {
                    control.rollOverAudio();
                    lbTastoHome.setIcon(imgTastoHomeGrande);
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseExited(MouseEvent e){
                lbTastoHome.setIcon(imgTastoHome);
            }
        });
    }

    public void riempimentoInfoContatto(String numero) throws SQLException {

        Contatti contatto = new Contatti();
        contatto = cercaInfoContattoDAO.cercaInfoContatti(numero, indirizzoSecondario, emailSecondario);
        getTxtNome().setText(contatto.getNome());
        getTxtCognome().setText(contatto.getCognome());
        getTxtCellulare().setText(contatto.getCellulare());
        getTxtFisso().setText(contatto.getFisso());
        getTxtEmail().setText(contatto.getEmail());
        getTxtIndirizzo().setText(contatto.getIndirizzo());

        nCellulare = getTxtCellulare().getText();
    }

    public void aggiornamentoContatto() throws SQLException {
        eliminaContattoDAO.eliminaContatto(nCellulare);
        creaContattoDAO.creaContatto(getTxtNome().getText(), getTxtCellulare().getText(), getTxtCognome().getText(), getTxtFisso().getText(), getTxtEmail().getText(), getTxtIndirizzo().getText(), null, null);
    }


    //GETTER SETTER


    public JLabel getLbTastoHome() {
        return lbTastoHome;
    }

    public void setLbTastoHome(JLabel lbTastoHome) {
        this.lbTastoHome = lbTastoHome;
    }

    public JButton getBtnAggiornaContatto() {
        return btnAggiornaContatto;
    }

    public void setBtnAggiornaContatto(JButton btnAggiornaContatto) {
        this.btnAggiornaContatto = btnAggiornaContatto;
    }

    public JButton getBtnEliminaContatto() {
        return btnEliminaContatto;
    }

    public void setBtnEliminaContatto(JButton btnEliminaContatto) {
        this.btnEliminaContatto = btnEliminaContatto;
    }

    public JPanel getSchedaInfoContattoPane() {
        return SchedaInfoContattoPane;
    }

    public void setSchedaInfoContattoPane(JPanel schedaInfoContattoPane) {
        SchedaInfoContattoPane = schedaInfoContattoPane;
    }

    public JComboBox getCbGruppo() {
        return cbGruppo;
    }

    public void setCbGruppo(JComboBox cbGruppo) {
        this.cbGruppo = cbGruppo;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public JTextField getTxtCognome() {
        return txtCognome;
    }

    public void setTxtCognome(JTextField txtCognome) {
        this.txtCognome = txtCognome;
    }

    public JTextField getTxtCellulare() {
        return txtCellulare;
    }

    public void setTxtCellulare(JTextField txtCellulare) {
        this.txtCellulare = txtCellulare;
    }

    public JTextField getTxtFisso() {
        return txtFisso;
    }

    public void setTxtFisso(JTextField txtFisso) {
        this.txtFisso = txtFisso;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JTextField getTxtIndirizzo() {
        return txtIndirizzo;
    }

    public void setTxtIndirizzo(JTextField txtIndirizzo) {
        this.txtIndirizzo = txtIndirizzo;
    }

    public JLabel getLbNome() {
        return lbNome;
    }

    public void setLbNome(JLabel lbNome) {
        this.lbNome = lbNome;
    }

    public JLabel getLbCognome() {
        return lbCognome;
    }

    public void setLbCognome(JLabel lbCognome) {
        this.lbCognome = lbCognome;
    }

    public JLabel getLbCellulare() {
        return lbCellulare;
    }

    public void setLbCellulare(JLabel lbCellulare) {
        this.lbCellulare = lbCellulare;
    }

    public JLabel getLbFisso() {
        return lbFisso;
    }

    public void setLbFisso(JLabel lbFisso) {
        this.lbFisso = lbFisso;
    }

    public JLabel getLbEmail() {
        return lbEmail;
    }

    public void setLbEmail(JLabel lbEmail) {
        this.lbEmail = lbEmail;
    }

    public JLabel getLbIndirizo() {
        return lbIndirizo;
    }

    public void setLbIndirizo(JLabel lbIndirizo) {
        this.lbIndirizo = lbIndirizo;
    }

    public JLabel getLbGruppo() {
        return lbGruppo;
    }

    public void setLbGruppo(JLabel lbGruppo) {
        this.lbGruppo = lbGruppo;
    }

    public JLabel getLbWhatsApp() {
        return lbWhatsApp;
    }

    public void setLbWhatsApp(JLabel lbWhatsApp) {
        this.lbWhatsApp = lbWhatsApp;
    }

    public JLabel getLbRispostaWA() {
        return lbRispostaWA;
    }

    public void setLbRispostaWA(JLabel lbRispostaWA) {
        this.lbRispostaWA = lbRispostaWA;
    }

    public JLabel getLbTelegram() {
        return lbTelegram;
    }

    public void setLbTelegram(JLabel lbTelegram) {
        this.lbTelegram = lbTelegram;
    }

    public JLabel getLbRispostaTG() {
        return lbRispostaTG;
    }

    public void setLbRispostaTG(JLabel lbRispostaTG) {
        this.lbRispostaTG = lbRispostaTG;
    }
}
