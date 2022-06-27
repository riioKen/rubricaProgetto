package Gui;

import Classi.Contatti;
import Controller.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import DAO.AggiornamentoContattoDAO;
import DAO.ContattoDAO;
import ImplementazioniDAO.AggiornamentoContattoPostreSQL;
import ImplementazioniDAO.ContattoPostgreSQL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SchedaInfoContatto {

    /////////////////////////////////////////////////////       ATTRIBUTI       /////////////////////////////////////////////////////
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
    private JButton btnImmagineCaricata;

    /////////////////////////////////////////////////////       OGGETTI     /////////////////////////////////////////////////////
    Controller control;

    ContattoDAO contattoDAO = new ContattoPostgreSQL();
    Contatti contatto = new Contatti();

    ArrayList<String> indirizzoSecondario = new ArrayList<>();
    ArrayList<String> emailSecondario = new ArrayList<>();
    ArrayList<String> gruppi = new ArrayList<>();
    JTextField[] piuEmail;
    JTextField[] piuIndirizzo;
    String nCellulare;

    /////////////////////////////////////////////////////       COSTRUTTORE     /////////////////////////////////////////////////////
    public  SchedaInfoContatto(Controller controller) throws SQLException {
        control = controller;
        funzionalitaTasti();

    }

    /////////////////////////////////////////////////////       FUNZIONALITA' PULSANTI GUI      /////////////////////////////////////////////////////
    public void funzionalitaTasti(){


        //FUNZIONALITA' TASTO btnEliminaContatto
        btnEliminaContatto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    control.clickAudio();
                    contattoDAO.eliminaContatto(contatto.getId());
                    control.getHomepage().stampaContatti();
                } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
                control.switchJPanelInView(control.getHomepage().getPaneBase());
            }
        });

        //FUNZIONALITA' TASTO btnAggiornaContatto
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

        //FUNZIONALITA' TASTO lbTastoHome
        ImageIcon imgTastoHome = new ImageIcon("Immagini/imgRitornoHomeFreccia24px.png");
        ImageIcon imgTastoHomeGrande = new ImageIcon("Immagini/imgRitornoHomeFreccia32px.png");
        lbTastoHome.setIcon(imgTastoHome);
        lbTastoHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.clickAudio();
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

        //FUNZIONALITA' TASTO btnImmagineCaricata
        btnImmagineCaricata.setMargin(new Insets(0,0,0,0));
        btnImmagineCaricata.setContentAreaFilled(false);
        btnImmagineCaricata.setBorderPainted(false);
        btnImmagineCaricata.setBorder(null);
        btnImmagineCaricata.setFocusPainted(false);
        btnImmagineCaricata.setOpaque(true);

        btnImmagineCaricata.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser("D:\\GitHub\\Java\\Rubrica - ProgettoOO_BD\\Immagini\\ImmaginiContatto");
                int valoreRitorno = fileChooser.showOpenDialog(null);
                if (valoreRitorno == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String percorsoAssoluto = selectedFile.getAbsolutePath();
                    ImageIcon immagineProfilo = new ImageIcon(percorsoAssoluto);
                    try {
                        btnImmagineCaricata.setIcon(immagineProfilo);
                        btnImmagineCaricata.setActionCommand(percorsoAssoluto);
                    } catch (Exception b) {
                        System.out.println("impossibile caricare l'immagine dal disco");
                    }
                }
            }
        });
    }

    /////////////////////////////////////////////////////       METODI LOGICI     /////////////////////////////////////////////////////
    public void riempimentoInfoContatto(int id) throws SQLException {
        int i = 0;
        indirizzoSecondario.clear();
        emailSecondario.clear();
        gruppi.clear();

        contatto.setId(id);
        System.out.println(contatto.getId());
        contatto = contattoDAO.cercaInfoContatti(id, indirizzoSecondario, emailSecondario, gruppi);
        getTxtNome().setText(contatto.getNome());
        getTxtCognome().setText(contatto.getCognome());
        getTxtCellulare().setText(contatto.getCellulare());
        getTxtFisso().setText(contatto.getFisso());
        getTxtEmail().setText(contatto.getEmail());
        getTxtIndirizzo().setText(contatto.getIndirizzo());

        //IMPLEMENTARE IL TASTO PER USCIRE SINGOLARMENTE DAL GRUPPO

        while(i < gruppi.size() ) {
            cbGruppo.addItem(gruppi.get(i));
            i++;
        }

        cbGruppo.addItem("...");
        //cbGruppo.getSelectedItem().;
        if(contatto.getFoto() != null) {
            ImageIcon immagine = new ImageIcon(contatto.getFoto());
            btnImmagineCaricata.setIcon(immagine);
        }
        else{
            ImageIcon immagine = new ImageIcon("Immagini/imgAggiungiFoto64pxScuro.png");
            btnImmagineCaricata.setIcon(immagine);
        }
        piuEmail = new JTextField[emailSecondario.size()];
        piuIndirizzo = new JTextField[indirizzoSecondario.size()];
        aggiuntaEmailSecondarie(piuEmail);
        aggiuntaIndirizziSecondari(piuIndirizzo);
        nCellulare = getTxtCellulare().getText();
    }
    //////////////////////////////////////////////////////// COMPLETARE IL METODO DI SOTTO ///////////////////////////////////////////////////////////////////////////////////
    public void aggiornamentoContatto() throws SQLException {
        AggiornamentoContattoDAO aggiornaContatto = new AggiornamentoContattoPostreSQL();

        contatto.setNome(getTxtNome().getText());
        contatto.setCognome(getTxtCognome().getText());
        contatto.setCellulare(getTxtCellulare().getText());
        contatto.setFisso(getTxtFisso().getText());
        contatto.setEmail(getTxtEmail().getText());
        contatto.setIndirizzo(getTxtIndirizzo().getText());
        contatto.setFoto(btnImmagineCaricata.getActionCommand());
        System.out.println(contatto.getId());


        aggiornaContatto.aggiornaContatto(contatto.getId(),contatto.getNome(), contatto.getCellulare(), contatto.getCognome(), contatto.getFisso(), contatto.getEmail(), contatto.getIndirizzo(), contatto.getFoto(), contatto.getNomeGruppo(), piuIndirizzo, piuEmail);
    }

    public void aggiuntaEmailSecondarie(JTextField[] piuEmail){
        for(int i = 0;  i < piuEmail.length; i++){
            piuEmail[i] = new JTextField();
            JPanel jpAppoggioPiuEmail = new JPanel();
            jpAppoggioPiuEmail.setLayout(new GridLayout(0,1));
            piuEmail[i].setText(emailSecondario.get(i));
            jpAppoggioPiuEmail.add(piuEmail[i]);
            jpPiuEmail.setLayout(new GridLayout(0,1));
            jpPiuEmail.add(jpAppoggioPiuEmail);

            jpPiuEmail.validate();
            jpPiuEmail.repaint();
        }
        emailSecondario.clear();

    }

    public void aggiuntaIndirizziSecondari(JTextField[] piuIndirizzo){
        for(int i = 0;  i < indirizzoSecondario.size(); i++){
            piuIndirizzo[i] = new JTextField();
            System.out.println("Debug riga 212 metodo aggiuntaIndirizziSecondari classe SchedaInfoContatto"+i);
            JPanel jpAppoggioPiuIndirizzo = new JPanel();
            jpAppoggioPiuIndirizzo.setLayout(new GridLayout(0,1));
            jpAppoggioPiuIndirizzo.add(piuIndirizzo[i]);
            jpPiuIndirizzo.setLayout(new GridLayout(0,1));
            jpPiuIndirizzo.add(jpAppoggioPiuIndirizzo);
            piuIndirizzo[i].setText(indirizzoSecondario.get(i));
            jpPiuIndirizzo.validate();
            jpPiuIndirizzo.repaint();
        }
        indirizzoSecondario.clear();
    }

    /////////////////////////////////////////////////////       GETTER SETTER       /////////////////////////////////////////////////////
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
