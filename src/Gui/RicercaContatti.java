package Gui;

import Classi.Contatti;
import Controller.Controller;
import DAO.RicercaContattoDAO;

import ImplementazioniDAO.RicercaContattoPostgreSQL;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RicercaContatti {

    Controller control;
    private JPanel ricercaContattiPane;
    private JTextField txtRicerca;
    private JRadioButton nomeRadioButton;
    private JRadioButton nicknameRadioButton;
    private JRadioButton emailRadioButton;
    private JRadioButton cellulareRadioButton;
    private JScrollPane risultatoScroll;
    private JPanel risultatoPane;

    ArrayList<Contatti> contattiInfo = new ArrayList<>();
    RicercaContattoDAO ricerca = new RicercaContattoPostgreSQL();
    public RicercaContatti(Controller controller) throws SQLException {
        control = controller;



        selezione();
        /*txtRicerca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(txtRicerca.getText().isBlank() || txtRicerca.getText().isEmpty() ) {
                    risultatoPane.removeAll();
                    risultatoScroll.revalidate();
                    ricercaContattiPane.revalidate();
                }
                super.keyTyped(e);
                try {
                    selezione();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });*/
    }

    private void selezione( ) throws SQLException {
        ButtonGroup bg1 = new ButtonGroup( );
        bg1.add(nomeRadioButton);
        bg1.add(nicknameRadioButton);
        bg1.add(emailRadioButton);
        bg1.add(cellulareRadioButton);

        txtRicerca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                /*if(txtRicerca.getText().isBlank() || txtRicerca.getText().isEmpty() ) {
                    risultatoPane.removeAll();
                    risultatoScroll.revalidate();
                    ricercaContattiPane.revalidate();
                }*/
                super.keyTyped(e);
                try {
                    if(bg1.isSelected(nomeRadioButton.getModel())){
                        contattiInfo.clear();
                        contattiInfo = ricerca.ricercaNome(txtRicerca.getText());
                        ricercaCampi(contattiInfo);
                    }
                    if(bg1.isSelected(emailRadioButton.getModel())){
                        contattiInfo.clear();
                        contattiInfo = ricerca.ricercaEmail(txtRicerca.getText());
                        ricercaCampi(contattiInfo);
                    }
                    if(bg1.isSelected(nicknameRadioButton.getModel())){
                        contattiInfo.clear();
                        contattiInfo = ricerca.ricercaNickname(txtRicerca.getText());
                        ricercaCampi(contattiInfo);
                    }
                    if(bg1.isSelected(cellulareRadioButton.getModel())){
                        contattiInfo.clear();
                        contattiInfo = ricerca.ricercaCellulare(txtRicerca.getText());
                        ricercaCampi(contattiInfo);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public void ricercaCampi(ArrayList<Contatti> contattiInfo) throws SQLException {
        risultatoPane.removeAll();

        int i = 0;
        risultatoPane.removeAll();
        while(i < contattiInfo.size()){
            JPanel paneLista = new JPanel();
            paneLista.setLayout(new GridLayout(0, 1));
            JButton btnSchedaContatto = new JButton();
            btnSchedaContatto.setText(contattiInfo.get(i).getNome() +" "+ contattiInfo.get(i).getCognome());//Per il momento mi accontento solo di dargli queste informazioni al JButton
            btnSchedaContatto.setActionCommand(contattiInfo.get(i).getCellulare());
            btnSchedaContatto.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        control.clickAudio();
                        control.newSchedaInfoContatto();    //Testing svuotamento dei campi nella scheda contatto
                        control.getSchedaInfoContatto().riempimentoInfoContatto(e.getActionCommand());
                        control.switchJPanelInView(control.getSchedaInfoContatto().getSchedaInfoContattoPane());
                        control.getHomepage().getJScrollBarListaContatti().setBorder(BorderFactory.createTitledBorder(""));
                        control.newRicercaContatti();
                    } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            paneLista.add(btnSchedaContatto);
            risultatoPane.setLayout(new GridLayout(0,1));
            risultatoPane.add(paneLista);


            risultatoPane.validate();
            i++;
        }
        ricercaContattiPane.repaint();
        ricercaContattiPane.validate();
        risultatoScroll.revalidate();
        ricercaContattiPane.revalidate();
    }

    //GETTER SETTER

    public JTextField getTxtRicerca() {
        return txtRicerca;
    }

    public void setTxtRicerca(JTextField txtRicerca) {
        this.txtRicerca = txtRicerca;
    }

    public JRadioButton getNomeRadioButton() {
        return nomeRadioButton;
    }

    public void setNomeRadioButton(JRadioButton nomeRadioButton) {
        this.nomeRadioButton = nomeRadioButton;
    }

    public JRadioButton getNicknameRadioButton() {
        return nicknameRadioButton;
    }

    public void setNicknameRadioButton(JRadioButton nicknameRadioButton) {
        this.nicknameRadioButton = nicknameRadioButton;
    }

    public JRadioButton getEmailRadioButton() {
        return emailRadioButton;
    }

    public void setEmailRadioButton(JRadioButton emailRadioButton) {
        this.emailRadioButton = emailRadioButton;
    }

    public JRadioButton getCellulareRadioButton() {
        return cellulareRadioButton;
    }

    public void setCellulareRadioButton(JRadioButton cellulareRadioButton) {
        this.cellulareRadioButton = cellulareRadioButton;
    }

    public JPanel getRicercaContattiPane() {
        return ricercaContattiPane;
    }

    public void setRicercaContattiPane(JPanel ricercaContattiPane) {
        this.ricercaContattiPane = ricercaContattiPane;
    }
}
