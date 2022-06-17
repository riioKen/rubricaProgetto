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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SchedaInfoContatto {

    private JPanel schedaInfoContattoPane;
    private JPanel jpPiuEmail;
    private JPanel jpPiuIndirizzo;

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
    private JComboBox cbGruppo;


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
                    control.clickAudio();
                    aggiornamentoContatto();
                } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
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
        //ADESSO BISOGNA INSERIRE SIA GLI ALTRI INDIRIZZI EMAIL CHE INDIRIZZI FISICI ALL'INTERFACCIA SCHEDAINFOCONTATTO TIPS HO 2 ARRAYLIST STRING DA USARE PER CAPIRE QUANTITA DI INFORMAZIONI
        Contatti contatto = new Contatti();
        contatto = cercaInfoContattoDAO.cercaInfoContatti(numero, indirizzoSecondario, emailSecondario);
        getTxtNome().setText(contatto.getNome());
        getTxtCognome().setText(contatto.getCognome());
        getTxtCellulare().setText(contatto.getCellulare());
        getTxtFisso().setText(contatto.getFisso());
        getTxtEmail().setText(contatto.getEmail());
        getTxtIndirizzo().setText(contatto.getIndirizzo());

        System.out.println("Controllo riga 156 classe SchedaInfoContatto\nindirizzi secondario"+" "+ indirizzoSecondario.size()+" email secondario"+" "+ emailSecondario.size());
        aggiuntaEmailSecondarie();
        aggiuntaIndirizziSecondari();
        nCellulare = getTxtCellulare().getText();
    }

    public void aggiuntaEmailSecondarie(){
        for(int i = 0;  i < emailSecondario.size(); i++){
            JTextField piuEmail = new JTextField();
            JPanel jpAppoggioPiuEmail = new JPanel();
            jpAppoggioPiuEmail.setLayout(new GridLayout(0,1));
            jpAppoggioPiuEmail.add(piuEmail);
            jpPiuEmail.setLayout(new GridLayout(0,1));
            jpPiuEmail.add(jpAppoggioPiuEmail);
            piuEmail.setText(emailSecondario.get(i));
            jpPiuEmail.validate();
            jpPiuEmail.repaint();
        }
        emailSecondario.clear();

    }

    public void aggiuntaIndirizziSecondari(){
        for(int i = 0;  i < indirizzoSecondario.size(); i++){
            System.out.println(i);
            JTextField piuIndirizzo = new JTextField();
            JPanel jpAppoggioPiuIndirizzo = new JPanel();
            jpAppoggioPiuIndirizzo.setLayout(new GridLayout(0,1));
            jpAppoggioPiuIndirizzo.add(piuIndirizzo);
            jpPiuIndirizzo.setLayout(new GridLayout(0,1));
            jpPiuIndirizzo.add(jpAppoggioPiuIndirizzo);
            piuIndirizzo.setText(indirizzoSecondario.get(i));
            jpPiuIndirizzo.validate();
            jpPiuIndirizzo.repaint();
        }
        indirizzoSecondario.clear();
    }
    public void aggiornamentoContatto() throws SQLException {
        eliminaContattoDAO.eliminaContatto(nCellulare);
        creaContattoDAO.creaContatto(getTxtNome().getText(), getTxtCellulare().getText(), getTxtCognome().getText(), getTxtFisso().getText(), getTxtEmail().getText(), getTxtIndirizzo().getText(), null, null);
    }


    //GETTER SETTER


    public JPanel getJpPiuEmail() {
        return jpPiuEmail;
    }

    public void setJpPiuEmail(JPanel jpPiuEmail) {
        this.jpPiuEmail = jpPiuEmail;
    }

    public JPanel getJpPiuIndirizzo() {
        return jpPiuIndirizzo;
    }

    public void setJpPiuIndirizzo(JPanel jpPiuIndirizzo) {
        this.jpPiuIndirizzo = jpPiuIndirizzo;
    }

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
        return schedaInfoContattoPane;
    }

    public void setSchedaInfoContattoPane(JPanel schedaInfoContattoPane) {
        this.schedaInfoContattoPane = schedaInfoContattoPane;
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
