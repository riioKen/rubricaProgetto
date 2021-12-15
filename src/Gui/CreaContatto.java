package Gui;

import Classi.Contatti;
import Controller.Controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class CreaContatto extends JFrame{
    //ATTRIBUTI
    private JTextField txtNome;
    private JTextField txtCognome;
    private JLabel lbNome;
    private JLabel lbIndirizzo;
    private JLabel lbCognome;
    private JLabel lbEmail;
    private JLabel lbCellulare;
    private JLabel lbFisso;
    private JTextField txtEmail;
    private JTextField txtIndirizzo;
    private JTextField txtCellulare;
    private JTextField txtFisso;
    private JPanel creaContatto;
    private JButton svuotaCampiButton;
    private JButton confermaButton;
    private JComboBox cbWhatsapp;
    private JComboBox cbTelegram;
    private JLabel lbTastoHome;


    Controller control;
    Homepage homepage = new Homepage();
    Contatti contatti;

    //Arraylist che contiene i nuovi dati inseriti dalla GUI CreaContatti
    static ArrayList<Contatti> insContatti = new ArrayList<>();

    public CreaContatto(Controller controller){

        control = controller;

        setContentPane(creaContatto);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocation(300, 300);

        TemaScuro(); //SET Tema scuro By DEFAULT

        lbTastoHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                control.cambioFinestra(control.getCreaContatto(), control.getHomepage());

            }
        });
        
        //Inserimento dei dati del contatto all'interno dell'Arraylist "insContatti"
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
<<<<<<< HEAD
               popolamentoArrayList();
=======
                insContattoArrayList();
>>>>>>> parent of ffed0de (Revert "Risolto "PARZIALMENTE" problema visualizzazione della lista contatti")
=======
                insContattoArrayList();
>>>>>>> parent of ffed0de (Revert "Risolto "PARZIALMENTE" problema visualizzazione della lista contatti")
            }
        });

        svuotaCampiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                svuotaCampi();
            }
        });


    }

    //COSTRUTTORE VUOTO
    public CreaContatto() {


    }

<<<<<<< HEAD
<<<<<<< HEAD
    //METODI
    public void popolamentoArrayList(){
=======
   //METODI

    public void insContattoArrayList(){
>>>>>>> parent of ffed0de (Revert "Risolto "PARZIALMENTE" problema visualizzazione della lista contatti")
=======
   //METODI

    public void insContattoArrayList(){
>>>>>>> parent of ffed0de (Revert "Risolto "PARZIALMENTE" problema visualizzazione della lista contatti")
        contatti = new Contatti();
        contatti.setNome(txtNome.getText());
        contatti.setCognome(txtCognome.getText());
        contatti.setEmail(txtEmail.getText());
        contatti.setCellulare(txtCellulare.getText());
        contatti.setFisso(txtFisso.getText());
        contatti.setIndirizzo(txtIndirizzo.getText());
        contatti.setWhatsapp(Objects.requireNonNull(cbWhatsapp.getSelectedItem()).toString()); //Objects.requireNonNull necessario in caso di valore NULL all'interno della CB

        insContatti.add(contatti);

<<<<<<< HEAD
<<<<<<< HEAD
=======

        //Svuotamento dei campo dopo inserimento
>>>>>>> parent of ffed0de (Revert "Risolto "PARZIALMENTE" problema visualizzazione della lista contatti")
=======

        //Svuotamento dei campo dopo inserimento
>>>>>>> parent of ffed0de (Revert "Risolto "PARZIALMENTE" problema visualizzazione della lista contatti")
        txtNome.setText("");
        txtCognome.setText("");
        txtCellulare.setText("");
        txtFisso.setText("");
        txtEmail.setText("");
        txtIndirizzo.setText("");
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> parent of ffed0de (Revert "Risolto "PARZIALMENTE" problema visualizzazione della lista contatti")
=======
    }

    public void svuotaCampi(){
        txtNome.setText("");
        txtCognome.setText("");
        txtCellulare.setText("");
        txtFisso.setText("");
        txtEmail.setText("");
        txtIndirizzo.setText("");
>>>>>>> parent of ffed0de (Revert "Risolto "PARZIALMENTE" problema visualizzazione della lista contatti")
    }

    public void svuotaCampi(){
        txtNome.setText("");
        txtCognome.setText("");
        txtCellulare.setText("");
        txtFisso.setText("");
        txtEmail.setText("");
        txtIndirizzo.setText("");
    }


    public void TemaScuro() {
        try{
            ImageIcon tastoHomeScuro = new ImageIcon("Immagini/imgRitornoHomeFrecciaSCURO.png");
            lbTastoHome.setIcon(tastoHomeScuro);
        }catch (Exception e) {
            System.out.println("L'immagine tastoHomeScuro non e' stata caricata correttamente");
        }
    }

    //GETTER SETTER

    public JButton getSvuotaCampiButton() {
        return svuotaCampiButton;
    }

    public void setSvuotaCampiButton(JButton svuotaCampiButton) {
        this.svuotaCampiButton = svuotaCampiButton;
    }

    public JButton getConfermaButton() {
        return confermaButton;
    }

    public void setConfermaButton(JButton confermaButton) {
        this.confermaButton = confermaButton;
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
}
