package Gui;

import Classi.Contatti;
import Controller.Controller;
import DAO.CreaContattoDAO;
import ImplementazioniDAO.CreaContattoPostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CreaContatto extends JPanel {
    //ATTRIBUTI
    private JPanel creaContatto;
    private JButton btnSvuotaCampi;
    private JButton btnConferma;
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
    private JLabel lbTastoHome;
    private JLabel lbErroreInserimento;
    private JLabel imgErroreNome;
    private JLabel imgErroreCognome;
    private JLabel imgErroreFisso;
    private JLabel imgErroreCellulare;
    private JLabel imgErroreEmail;
    private JLabel imgErroreIndirizzo;

    //OGGETTI
    Controller control;
    Contatti contatti;


    //Arraylist per stampa, DEBUG, dovrà essere sostituito con inserimento da DATABASE
    static ArrayList<Contatti> insContatti = new ArrayList<>();
    static ArrayList<Contatti> insContattiCopia = new ArrayList<>();

    //COSTRUTTORE
    public CreaContatto(Controller controller) {
        control = controller;

        TemaScuro();
        messaggiErroreFALSE();
        lbTastoHome();
        btnConferma();
        btnSvuotaCampi();
    }



    //FUNZIONALITA' PULSANTI GUI
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
        String contattoInseritoDB = "Contatto Inserito nel db";

                getBtnConferma().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        messaggiErroreFALSE();
                        creaContatto.repaint();
                        if(txtNome.getText().length() > 20 || txtNome.getText().length() == 0){
                            lbErroreInserimento(errNomeLungo);
                            imgErroreNome.setVisible(true);
                        }else if(txtCognome.getText().length() > 20 || txtCognome.getText().length() == 0){
                            lbErroreInserimento(errCognomeLungo);
                            imgErroreCognome.setVisible(true);
                        } else if(txtCellulare.getText().length() != 10){
                            lbErroreInserimento(errCellulareNonValido);
                            imgErroreCellulare.setVisible(true);
                        } else if(txtFisso.getText().length() > 10 || txtFisso.getText().length() == 0){
                            lbErroreInserimento(errFissoNonValido);
                            imgErroreFisso.setVisible(true);
                        } else if(txtEmail.getText().length() > 30 || txtEmail.getText().length() == 0){
                            lbErroreInserimento(errEmailNonValido);
                            imgErroreEmail.setVisible(true);
                        }else if(controlliIndirizzo()){
                            lbErroreInserimento(errIndirizzoNonValido);
                            imgErroreIndirizzo.setVisible(true);
                        } else{
                            lbErroreInserimento(contattoInseritoDB);
                            try {
                                inserimentoContattoDatabase();
                                control.getHomepage().stampaContatti();
                                control.switchJPanelInView(control.getHomepage().getPaneBase());

                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }

    public boolean controlliIndirizzo(){
        String via, civico, cap, citta, nazione;
        String[] divisione = txtIndirizzo.getText().split("\\s*,\\s*");

        if(txtIndirizzo.getText().length() != 0) {

            via = divisione[0];
            if (via.length() > 30 || via.length() == 0) {
                return true;
            }

            civico = divisione[1];
            if (civico.length() > 3 || civico.length() == 0)
                return true;

            cap = divisione[2];
            if (cap.length() != 5)
                return true;

            citta = divisione[3];
            if (citta.length() > 20 || citta.length() == 0)
                return true;

            nazione = divisione[4];
            if (nazione.length() > 20 || nazione.length() == 0)
                return true;

        }
        else{
            return true;
        }
        return false;
    }

    public void lbErroreInserimento(String codiceErrore){

        lbErroreInserimento.setText(codiceErrore);
        lbErroreInserimento.setVisible(true);
    }
    public void lbTastoHome(){
        getLbTastoHome().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                control.switchJPanelInView(control.getHomepage().getPaneBase());
                try {
                    control.getHomepage().stampaContatti();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                control.getHomepage().getJScrollBarListaContatti().setBorder(BorderFactory.createTitledBorder("Lista dei Contatti"));//TESTING
            }

        });
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

        creaContattoDAO.creaContatto(contatti.getNome(), contatti.getCellulare(), contatti.getCognome(), contatti.getFisso(), contatti.getEmail(), contatti.getIndirizzo());

        svuotaCampi();
        control.getHomepage().stampaContatti();
    }

    //STAMPA DIRETTA *** NON DAL DATABASE ***   DEBUGGING PURPOSE
    /*public void stampaContatti(){
        int i = 0;
        while(i < insContattiCopia.size()){
            JPanel paneLista = new JPanel();
            paneLista.setLayout(new GridLayout(0, 1));
            JButton btnSchedaContatto = new JButton();
            btnSchedaContatto.setText(CreaContatto.getInsContattiCopia().get(i).getNome() +" "+ CreaContatto.getInsContattiCopia().get(i).getCognome()); //Per il momento mi accontento solo di dargli queste informazioni al JButton
            paneLista.add(btnSchedaContatto);
            control.getHomepage().getPaneBase().setLayout(new GridLayout(0,1));
            control.getHomepage().getPaneBase().add(paneLista);

            insContattiCopia.remove(i);
            validate();
            i++;
        }
    }*/


    public void TemaScuro() {

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


        try {
            ImageIcon tastoHomeScuro = new ImageIcon("Immagini/imgRitornoHomeFrecciaSCURO.png");
            lbTastoHome.setIcon(tastoHomeScuro);
        } catch (Exception e) {
            System.out.println("L'immagine tastoHomeScuro non e' stata caricata correttamente");
        }
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

    public JLabel getLbTastoHome() {
        return lbTastoHome;
    }

    public void setLbTastoHome(JLabel lbTastoHome) {
        this.lbTastoHome = lbTastoHome;
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
