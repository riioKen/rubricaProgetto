package Gui;

import Classi.Contatti;
import Controller.Controller;
import DAO.StampaContattoDAO;
import ImplementazioniDAO.StampaContattoPostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    //OGGETTI
    Controller control;
    Contatti contatti;

    //Arraylist per stampa, DEBUG, dovrà essere sostituito con inserimento da DATABASE
    static ArrayList<Contatti> insContatti = new ArrayList<>();
    static ArrayList<Contatti> insContattiCopia = new ArrayList<>();

    //COSTRUTTORE
    public CreaContatto(Controller controller) {
        control = controller;

        setVisible(true);
        TemaScuro();


        getLbTastoHome().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //control.switchJPanel(control.getHomepage().getHomepage());
                control.switchCreaContatto(control.getHomepage().getPaneBase());
                control.getHomepage().getJScrollBarListaContatti().setBorder(BorderFactory.createTitledBorder("Lista dei Contatti"));//TESTING
            }

        });

        getBtnConferma().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popolamentoArrayList();
            }
        });

        getBtnSvuotaCampi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                svuotaCampi();
            }
        });

    }


    //METODI


    public void popolamentoArrayList() {
        contatti = new Contatti();
        contatti.setNome(txtNome.getText());
        contatti.setCognome(txtCognome.getText());
        contatti.setEmail(txtEmail.getText());
        contatti.setCellulare(txtCellulare.getText());
        contatti.setFisso(txtFisso.getText());
        contatti.setIndirizzo(txtIndirizzo.getText());
        contatti.setWhatsapp(Objects.requireNonNull(cbWhatsapp.getSelectedItem()).toString()); //Objects.requireNonNull necessario in caso di valore NULL all'interno della CB

        insContatti.add(contatti);
        insContattiCopia.add(contatti);

        svuotaCampi();

        //stampaContatti();
    }

    //STAMPA DIRETTA *** NON DAL DATABASE ***
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

    public void svuotaCampi() {
        txtNome.setText("");
        txtCognome.setText("");
        txtCellulare.setText("");
        txtFisso.setText("");
        txtEmail.setText("");
        txtIndirizzo.setText("");
    }


    public void TemaScuro() {
        try {
            ImageIcon tastoHomeScuro = new ImageIcon("Immagini/imgRitornoHomeFrecciaSCURO.png");
            lbTastoHome.setIcon(tastoHomeScuro);
        } catch (Exception e) {
            System.out.println("L'immagine tastoHomeScuro non e' stata caricata correttamente");
        }
    }

    //GETTER SETTER



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

    public void setBtnConferma(JButton btnConferma) {
        popolamentoArrayList();
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
