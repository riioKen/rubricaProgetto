package Gui;

import Classi.Contatti;
import Controller.Controller;
import DAO.SchedaRicercheDAO;
import ImplementazioniDAO.SchedaRicerchePostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class SchedaRicerche {
    private JPanel paneRicercaMain;
    private JPanel paneRicerca;
    private JPanel paneBaseRicerca;
    private JScrollPane JScrollRicerca;
    private JTextField txtRicerca;
    private JButton btnRicercaNome;
    private JButton btnRicercaCellulare;
    private JButton btnRicercaNickname;
    private JButton btnRicercaEmail;

    Controller control;

    ArrayList<Contatti> ricercaDB = new ArrayList<>();

    public SchedaRicerche(Controller controller){

        control = controller;
        ricercaNome();
        ricercaEmail();
        ricercaCellulare();
        ricercaNickname();
        textureTasti();
    }

    public void ricercaNome(){
        btnRicercaNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stampaRicercaNome();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public void ricercaEmail(){
        btnRicercaEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stampaRicercaEmail();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public void ricercaNickname(){
        btnRicercaNickname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stampaRicercaNickname();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public void ricercaCellulare(){
        btnRicercaCellulare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    stampaRicercaCellulare();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void stampaRicercaNome() throws SQLException {

        ricercaDB.clear();
        SchedaRicercheDAO stampaRicerche = new SchedaRicerchePostgreSQL();
        ricercaDB = stampaRicerche.ricercaNome(txtRicerca.getText());
        paneBaseRicerca.removeAll();
        int i = 0;
        JPanel paneLista = new JPanel();
        paneLista.setLayout(new GridLayout(0,1));
        paneBaseRicerca.setLayout(new GridLayout(0,1));
        while(i < ricercaDB.size()){

            JButton btnContatto = new JButton();
            btnContatto.setFocusable(false);
            btnContatto.setText(ricercaDB.get(i).getNome() +" "+ ricercaDB.get(i).getCognome());
            paneLista.add(btnContatto);
            paneBaseRicerca.add(paneLista);
            i++;

        }
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));

        paneBaseRicerca.repaint();
        paneBaseRicerca.validate();

    }

    public void stampaRicercaNickname() throws SQLException {

        ricercaDB.clear();
        SchedaRicercheDAO stampaRicerche = new SchedaRicerchePostgreSQL();
        ricercaDB = stampaRicerche.ricercaNickname(txtRicerca.getText());
        paneBaseRicerca.removeAll();
        int i = 0;
        JPanel paneLista = new JPanel();
        paneLista.setLayout(new GridLayout(0,1));
        paneBaseRicerca.setLayout(new GridLayout(0,1));
        while(i < ricercaDB.size()){

            JButton btnContatto = new JButton();
            btnContatto.setText(ricercaDB.get(i).getNome() +" "+ ricercaDB.get(i).getCognome());
            paneLista.add(btnContatto);
            paneBaseRicerca.add(paneLista);
            i++;

        }
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));

        paneBaseRicerca.repaint();
        paneBaseRicerca.validate();

    }
    public void stampaRicercaCellulare() throws SQLException {

        ricercaDB.clear();
        SchedaRicercheDAO stampaRicerche = new SchedaRicerchePostgreSQL();
        ricercaDB = stampaRicerche.ricercaCellulare(txtRicerca.getText());
        paneBaseRicerca.removeAll();
        int i = 0;
        JPanel paneLista = new JPanel();
        paneLista.setLayout(new GridLayout(0,1));
        paneBaseRicerca.setLayout(new GridLayout(0,1));
        while(i < ricercaDB.size()){

            JButton btnContatto = new JButton();
            btnContatto.setText(ricercaDB.get(i).getNome() +" "+ ricercaDB.get(i).getCognome());
            paneLista.add(btnContatto);
            paneBaseRicerca.add(paneLista);
            i++;

        }
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));

        paneBaseRicerca.repaint();
        paneBaseRicerca.validate();

    }
    public void stampaRicercaEmail() throws SQLException {

        ricercaDB.clear();
        SchedaRicercheDAO stampaRicerche = new SchedaRicerchePostgreSQL();
        ricercaDB = stampaRicerche.ricercaEmail(txtRicerca.getText());
        paneBaseRicerca.removeAll();
        int i = 0;
        JPanel paneLista = new JPanel();
        paneLista.setLayout(new GridLayout(0,1));
        paneBaseRicerca.setLayout(new GridLayout(0,1));
        while(i < ricercaDB.size()){

            JButton btnContatto = new JButton();
            btnContatto.setText(ricercaDB.get(i).getNome() +" "+ ricercaDB.get(i).getCognome());
            paneLista.add(btnContatto);
            paneBaseRicerca.add(paneLista);
            i++;

        }
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));
        paneLista.add(Box.createVerticalStrut(100));

        paneBaseRicerca.repaint();
        paneBaseRicerca.validate();

    }

    public void textureTasti(){

        btnRicercaCellulare.setFocusable(false);
        btnRicercaNome.setFocusable(false);
        btnRicercaEmail.setFocusable(false);
        btnRicercaNickname.setFocusable(false);
        //txtRicerca.setFocusable(false);
    }


    //GETTER SETTER

    public JTextField getTxtRicerca() {
        return txtRicerca;
    }

    public void setTxtRicerca(JTextField txtRicerca) {
        this.txtRicerca = txtRicerca;
    }

    public JButton getBtnRicercaNome() {
        return btnRicercaNome;
    }

    public void setBtnRicercaNome(JButton btnRicercaNome) {
        this.btnRicercaNome = btnRicercaNome;
    }

    public JPanel getPaneRicercaMain() {
        return paneRicercaMain;
    }

    public void setPaneRicercaMain(JPanel paneRicercaMain) {
        this.paneRicercaMain = paneRicercaMain;
    }

    public JPanel getPaneRicerca() {
        return paneRicerca;
    }

    public void setPaneRicerca(JPanel paneRicerca) {
        this.paneRicerca = paneRicerca;
    }

    public JScrollPane getJScrollRicerca() {
        return JScrollRicerca;
    }

    public void setJScrollRicerca(JScrollPane JScrollRicerca) {
        this.JScrollRicerca = JScrollRicerca;
    }

    public JPanel getPaneBaseRicerca() {
        return paneBaseRicerca;
    }

    public void setPaneBaseRicerca(JPanel paneBaseRicerca) {
        this.paneBaseRicerca = paneBaseRicerca;
    }
}
