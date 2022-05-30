package Gui;

import Classi.Contatti;
import Controller.Controller;
import DAO.CreaContattoDAO;
import ImplementazioniDAO.CreaContattoPostgreSQL;
import com.formdev.flatlaf.FlatLightLaf;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CreaContatto extends JPanel {
    //ATTRIBUTI
    private JPanel creaContatto;
    private JPanel jpTxtEmail;
    private JPanel jpTxtIndirizzo;
    private JButton btnSvuotaCampi;
    private JButton btnConferma;
    private JButton btnTastoHome;
    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtEmail;
    private JTextField txtIndirizzo;
    private JTextField txtCellulare;
    private JTextField txtFisso;
    private JLabel lbNome;
    private JLabel lbIndirizzo;
    private JLabel lbCognome;
    private JLabel lbEmail;
    private JLabel lbCellulare;
    private JLabel lbFisso;
    private JComboBox cbWhatsapp;
    private JComboBox cbTelegram;
    private JLabel lbErroreInserimento;
    private JLabel imgErroreNome;
    private JLabel imgErroreCognome;
    private JLabel imgErroreFisso;
    private JLabel imgErroreCellulare;
    private JLabel imgErroreEmail;
    private JLabel imgErroreIndirizzo;
    private JButton btnPiuEmail;
    private JButton btnPiuIndirizzi;

    //OGGETTI
    Controller control;
    Contatti contatti;


    //Arraylist per stampa, DEBUG, dovrà essere sostituito con inserimento da DATABASE
    static ArrayList<Contatti> insContatti = new ArrayList<>();
    static ArrayList<Contatti> insContattiCopia = new ArrayList<>();
    ArrayList<JTextField> listaTxtEmail = new ArrayList<>();
    ArrayList<JTextField> listaTxtIndirizzo = new ArrayList<>();

    //COSTRUTTORE
    public CreaContatto(Controller controller) {
        control = controller;

        temaScuro();
        messaggiErroreFALSE();
        funzionalitaTasti();

    }

    //FUNZIONALITA' PULSANTI GUI
    public void funzionalitaTasti(){
        btnConferma();
        btnSvuotaCampi();
    }
    public void btnSvuotaCampi(){
        getBtnSvuotaCampi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                svuotaCampi();
            }
        });
    }
    public void btnConferma(){
        String errNomeLungo = "Hai inserito un nome non valido";
        String errCognomeLungo = "Hai inserito un cognome non valido";
        String errCellulareNonValido = "Hai inserito un numero cellulare non valido";
        String errFissoNonValido = "Hai inserito un fisso non valido";
        String errEmailNonValido = "Hai inserito un indirizzo email non valido";
        String errIndirizzoNonValido = "Hai inserito un indirizzo non valido";
        String errSvuota = "";
                getBtnConferma().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        messaggiErroreFALSE();
                        creaContatto.repaint();
                        if(txtNome.getText().length() > 20 || txtNome.getText().length() == 0){
                            lbErroreInserimento(errNomeLungo);
                            imgErroreNome.setVisible(true);
                        } else if(txtCognome.getText().length() > 20 || txtCognome.getText().length() == 0){
                            lbErroreInserimento(errCognomeLungo);
                            imgErroreCognome.setVisible(true);
                        }else if(txtCellulare.getText().length() != 10){
                            lbErroreInserimento(errCellulareNonValido);
                            imgErroreCellulare.setVisible(true);
                        } else if((txtFisso.getText().length() < 8 || txtFisso.getText().length() > 11) || txtFisso.getText().length() == 0){
                            lbErroreInserimento(errFissoNonValido);
                            imgErroreFisso.setVisible(true);
                        }else if(txtEmail.getText().length() > 30 || txtEmail.getText().length() == 0){
                            lbErroreInserimento(errEmailNonValido);
                            imgErroreEmail.setVisible(true);
                        } else if(!controlliIndirizzo()){
                            lbErroreInserimento(errIndirizzoNonValido);
                            imgErroreIndirizzo.setVisible(true);
                        } else{
                            try {
                                    control.clickAudio();
                                    inserimentoContattoDatabase();
                                    control.getHomepage().stampaContatti();
                                    control.switchJPanelInView(control.getHomepage().getPaneBase());
                                    lbErroreInserimento(errSvuota);

                            } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }

    public boolean controlliIndirizzo() {
        String via, civico, cap, citta, nazione;
        int lunghezza = txtIndirizzo.getText().split("\\s*,\\s*").length;
        String[] divisione;
        if (lunghezza == 5) {
            divisione = txtIndirizzo.getText().split("\\s*,\\s*");
            via = divisione[0];
            civico = divisione[1];
            cap = divisione[2];
            citta = divisione[3];
            nazione = divisione[4];
            if ((via.length() < 30 && via.length() > 0) && (civico.length() <= 3 && civico.length() > 0) && (cap.length() == 5) && (citta.length() < 20 && citta.length() > 0) && (nazione.length() < 20 && nazione.length() > 0)) {
                return true;
            }
            else {
                return false;
            }
        }

        return false;
    }

    public void lbErroreInserimento(String codiceErrore){

        lbErroreInserimento.setText(codiceErrore);
        lbErroreInserimento.setVisible(true);
    }

    public void svuotaCampi() {
        txtNome.setText("");
        txtCognome.setText("");
        txtCellulare.setText("");
        txtFisso.setText("");
        txtEmail.setText("");
        txtIndirizzo.setText("");

        messaggiErroreFALSE();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //METODI

    public void aggiuntaTxtFieldIndirizzo(){      //RISOLVERE PIUINDIRIZZI JTXT SPORCO DOPO CONFERMA
        JTextField txtPiuIndirizzi = new JTextField();
        JPanel appoggioIndirizzo = new JPanel();
        appoggioIndirizzo.setLayout(new GridLayout(0,1));
        appoggioIndirizzo.add(txtPiuIndirizzi);
        jpTxtIndirizzo.setLayout(new GridLayout(0,1));
        jpTxtIndirizzo.add(appoggioIndirizzo);

        listaTxtIndirizzo.add(txtPiuIndirizzi);
        System.out.println(txtPiuIndirizzi.getText());

        creaContatto.repaint();
        creaContatto.validate();
    }

    public void messaggiErroreFALSE(){

        imgErroreFisso.setVisible(false);
        imgErroreCognome.setVisible(false);
        imgErroreNome.setVisible(false);
        imgErroreCellulare.setVisible(false);
        imgErroreIndirizzo.setVisible(false);
        imgErroreEmail.setVisible(false);
    }
    public void inserimentoContattoDatabase() throws SQLException {
        CreaContattoDAO creaContattoDAO = new CreaContattoPostgreSQL();
        contatti = new Contatti();
        contatti.setNome(txtNome.getText());
        contatti.setCognome(txtCognome.getText());
        contatti.setCellulare(txtCellulare.getText());
        contatti.setFisso(txtFisso.getText());
        contatti.setEmail(txtEmail.getText());
        contatti.setIndirizzo(txtIndirizzo.getText());

        contatti.setWhatsapp(Objects.requireNonNull(cbWhatsapp.getSelectedItem()).toString()); //Objects.requireNonNull necessario in caso di valore NULL all'interno della CB

        creaContattoDAO.creaContatto(contatti.getNome(), contatti.getCellulare(), contatti.getCognome(), contatti.getFisso(), contatti.getEmail(), contatti.getIndirizzo(), listaTxtIndirizzo, listaTxtEmail);

        svuotaCampi();
        control.getHomepage().stampaContatti();
    }

    public void temaScuro() {

        ImageIcon imgErrore = new ImageIcon("Immagini/imgErroreCampo.png");
        Image image = imgErrore.getImage();
        Image imgScalata = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        imgErrore = new ImageIcon(imgScalata);

        try{
            imgErroreNome.setIcon(imgErrore);
            imgErroreCognome.setIcon(imgErrore);
            imgErroreFisso.setIcon(imgErrore);
            imgErroreCellulare.setIcon(imgErrore);
            imgErroreEmail.setIcon(imgErrore);
            imgErroreIndirizzo.setIcon(imgErrore);
        }catch(Exception e){
            System.out.println("Una delle immagini imgErrore in CreaContatto non funziona");
        }

        textureTasti();
    }

    public void temaChiaro(){   //TESTING
        FlatLightLaf.setup();
        SwingUtilities.updateComponentTreeUI(creaContatto);
    }
    public void textureTasti(){

        //btnTastoHome
        ImageIcon imgTastoHome = new ImageIcon("Immagini/imgRitornoHomeFreccia24px.png");
        ImageIcon imgTastoHomeGrande = new ImageIcon("Immagini/imgRitornoHomeFreccia32px.png");

        btnTastoHome.setIcon(imgTastoHome);
        btnTastoHome.setMargin(new Insets(0,0,0,0));
        btnTastoHome.setContentAreaFilled(false);
        btnTastoHome.setBorderPainted(false);
        btnTastoHome.setBorder(null);
        btnTastoHome.setFocusPainted(false);
        btnTastoHome.setOpaque(true);


        btnTastoHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.switchJPanelInView(control.getHomepage().getPaneBase());
                try {
                    control.rollOverAudio();
                    control.getHomepage().stampaContatti();

                } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
                control.getHomepage().getJScrollBarListaContatti().setBorder(BorderFactory.createTitledBorder("Lista dei Contatti"));//TESTING
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnTastoHome.setIcon(imgTastoHomeGrande);
                try {
                    control.rollOverAudio();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnTastoHome.setIcon(imgTastoHome);
            }
        });

        //btnPiuIndirizzi
        ImageIcon piuIndirizzi = new ImageIcon("Immagini/imgIconaAggiungiIndirizzo-Email16px.png");
        ImageIcon piuIndirizziGrande = new ImageIcon("Immagini/imgIconaAggiungiIndirizzo-Email24px.png");

        btnPiuIndirizzi.setIcon(piuIndirizzi);
        btnPiuIndirizzi.setMargin(new Insets(0,0,0,0));
        btnPiuIndirizzi.setContentAreaFilled(false);
        btnPiuIndirizzi.setBorderPainted(false);
        btnPiuIndirizzi.setBorder(null);
        btnPiuIndirizzi.setFocusPainted(false);
        btnPiuIndirizzi.setOpaque(true);

        btnPiuIndirizzi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.rollOverAudio();

                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
                aggiuntaTxtFieldIndirizzo();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnPiuIndirizzi.setIcon(piuIndirizziGrande);
                try {
                    control.rollOverAudio();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
                btnPiuIndirizzi.setIcon(piuIndirizzi);
            }
        });

        //btnPiuEmail
        ImageIcon piuEmail = new ImageIcon("Immagini/imgIconaAggiungiIndirizzo-Email16px.png");
        ImageIcon piuEmailGrande = new ImageIcon("Immagini/imgIconaAggiungiIndirizzo-Email24px.png");

        btnPiuEmail.setIcon(piuIndirizzi);
        btnPiuEmail.setMargin(new Insets(0,0,0,0));
        btnPiuEmail.setContentAreaFilled(false);
        btnPiuEmail.setBorderPainted(false);
        btnPiuEmail.setBorder(null);
        btnPiuEmail.setFocusPainted(false);
        btnPiuEmail.setOpaque(true);

        btnPiuEmail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    control.rollOverAudio();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
                aggiuntaTxtFieldEmail();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnPiuEmail.setIcon(piuEmailGrande);
                try {
                    control.rollOverAudio();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
                btnPiuEmail.setIcon(piuEmail);
            }
        });

    }
    public void aggiuntaTxtFieldEmail(){
        JTextField txtEmail = new JTextField();
        JPanel appoggioEmail = new JPanel();
        appoggioEmail.setLayout(new GridLayout(0,1));
        appoggioEmail.add(txtEmail);
        jpTxtEmail.setLayout(new GridLayout(0,1));
        jpTxtEmail.add(appoggioEmail);

        listaTxtEmail.add(txtEmail);

        jpTxtEmail.repaint();
        jpTxtEmail.validate();
        creaContatto.repaint();
        creaContatto.validate();
    }

    //GETTER SETTER

    public JLabel getLbErroreInserimento() {
        return lbErroreInserimento;
    }

    public void setLbErroreInserimento(JLabel lbErroreInserimento) {
        this.lbErroreInserimento = lbErroreInserimento;
    }

    public static ArrayList<Contatti> getInsContattiCopia() {
        return insContattiCopia;
    }

    public static void setInsContattiCopia(ArrayList<Contatti> insContattiCopia) {
        CreaContatto.insContattiCopia = insContattiCopia;
    }

    public JButton getBtnSvuotaCampi() {
        return btnSvuotaCampi;
    }

    public void setBtnSvuotaCampi(JButton btnSvuotaCampi) {
        svuotaCampi();
    }

    public JButton getBtnConferma() {
        return btnConferma;
    }

    public void setBtnConferma(JButton btnConferma) throws SQLException {
        inserimentoContattoDatabase();
    }


    public JComboBox getCbWhatsapp() {
        return cbWhatsapp;
    }

    public void setCbWhatsapp(JComboBox cbWhatsapp) {
        this.cbWhatsapp = cbWhatsapp;
    }

    public JComboBox getCbTelegram() {
        return cbTelegram;
    }

    public void setCbTelegram(JComboBox cbTelegram) {
        this.cbTelegram = cbTelegram;
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

    public JLabel getLbNome() {
        return lbNome;
    }

    public void setLbNome(JLabel lbNome) {
        this.lbNome = lbNome;
    }

    public JLabel getLbIndirizzo() {
        return lbIndirizzo;
    }

    public void setLbIndirizzo(JLabel lbIndirizzo) {
        this.lbIndirizzo = lbIndirizzo;
    }

    public JLabel getLbCognome() {
        return lbCognome;
    }

    public void setLbCognome(JLabel lbCognome) {
        this.lbCognome = lbCognome;
    }

    public JLabel getLbEmail() {
        return lbEmail;
    }

    public void setLbEmail(JLabel lbEmail) {
        this.lbEmail = lbEmail;
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

    public JPanel getCreaContatto() {
        return creaContatto;
    }

    public void setCreaContatto(JPanel creaContatto) {
        this.creaContatto = creaContatto;
    }

    public static ArrayList<Contatti> getInsContatti() {
        return insContatti;
    }

    public static void setInsContatti(ArrayList<Contatti> insContatti) {
        CreaContatto.insContatti = insContatti;
    }
    
}
