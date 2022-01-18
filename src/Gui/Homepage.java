package Gui;

import Classi.*;
import Controller.*;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.StampaContattoDAO;
import ImplementazioniDAO.*;

public class Homepage extends JFrame {

    //ATTRIBUTI
    private JPanel cardHomepage;
    private JPanel homepage;
    private JPanel paneBase;
    private JPanel paneTendina;
    private JScrollPane JScrollBarListaContatti;
    private JButton btnCreaNuovoContatto;
    private JButton btnEliminaContatto;
    private JButton btnSwitchTema;
    private JButton stampaListaContattiButton;
    private JButton lineeApertura;
    private JButton lineeChiusura;

    //OGGETTI
    private Timer timer;
    Controller control;
    int posizioneTendina = 50;

    static ArrayList<Contatti> contattiDB = new ArrayList<>();


    public Homepage(Controller controller) throws SQLException {
        control = controller; //Serve a linkare il controller al JPanel

        cardHomepage = new JPanel();
        cardHomepage.setLayout(new CardLayout());
        cardHomepage.add(homepage);

        setTitle("Homepage");
        setContentPane(cardHomepage);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Serve a terminare il programma quando si preme la X
        setLocation(300, 300);


        TemaScuro();  //INIZIALIZAZZIONE DEL TEMA SCURO BY DEFAULT


        //TIMER CHE FA DA SWITCH PER LA DARK MODE E LA LIGHT MODE
        btnSwitchTema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerSwitchTema();
            }
        });

        //
        btnCreaNuovoContatto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //control.switchJPanel(control.getCreaContatto().getCreaContatto());
                control.switchCreaContatto(control.getCreaContatto().getCreaContatto()); //TESTING
                JScrollBarListaContatti.setBorder(BorderFactory.createTitledBorder(""));
            }
        });


        //GESTIONE TENDINA SCORRIMENTO
        lineeChiusura.setVisible(false);
        lineeApertura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriTendina();
            }
        });

        //Stampa dei contatti dal database ******** TESTING ********

        stampaContatti();

    }

    //METODI

    public void stampaContatti() throws SQLException {
        StampaContattoDAO stampaContatto = new StampaContattoPostgreSQL();
        contattiDB = stampaContatto.stampaContatti();
        int i = 0;
        paneBase.removeAll();
        while(i < contattiDB.size()){
            JPanel paneLista = new JPanel();
            paneLista.setLayout(new GridLayout(0, 1));
            JButton btnSchedaContatto = new JButton();
            btnSchedaContatto.setText(contattiDB.get(i).getNome() +" "+ contattiDB.get(i).getCognome()); //Per il momento mi accontento solo di dargli queste informazioni al JButton
            paneLista.add(btnSchedaContatto);
            paneBase.setLayout(new GridLayout(0,1));
            paneBase.add(paneLista);

            validate();
            i++;
        }
        paneBase.repaint();
        paneBase.validate();
        contattiDB.clear();

    }


    public void apriTendina() {
        if (posizioneTendina == 50) {
            paneTendina.setSize(posizioneTendina, 150);
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 50; i <= 150; i = i + 5) {
                            Thread.sleep(0, 01);
                            paneTendina.setSize(i - 2, 150);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    lineeChiusura.setVisible(true);
                }
            };
            th.start();
            posizioneTendina = 150;
        }
    }


    public void timerSwitchTema() {
        if (timer == null) {
            timer = new Timer(100, new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                }
            });
            timer.start();
            TemaChiaro();
        } else if (timer.isRunning()) {
            timer.stop();
            TemaScuro();
        } else {
            timer.start();
            TemaChiaro();
        }
    }

    //TEMA SCURO SET BY DEFAULT
    public void TemaScuro() {
        FlatDarculaLaf.setup();
        SwingUtilities.updateComponentTreeUI(homepage);

        //GUI HOMEPAGE
        try {
            Image imgSwitchTema = ImageIO.read(new File("Immagini/imgTemaScuro.png")); //Da settare la dimensione delle ICONE (PRESI DI MISURA GIUSTA COSI DA NON
            btnSwitchTema.setIcon(new ImageIcon(imgSwitchTema));
        } catch (Exception e) {                                                                                //AVERE NECESSITA' PER IL MOMENTO)
            System.out.println("Icona btnTemaScuro non caricata");
        }
        btnSwitchTema.setFocusable(false);

        //Modifica del JButton ********* TESTING **********
        try {
            Image imgCreaNuovoContatto = ImageIO.read(new File("Immagini/imgCreaNuovoContatto.png")); //Da settare la dimensione (PRESI DI MISURA GIUSTA COSI DA NON
            btnCreaNuovoContatto.setIcon(new ImageIcon(imgCreaNuovoContatto));
        } catch (Exception e) {                                                                                //AVERE NECESSITA' PER IL MOMENTO)
            System.out.println("Icona btnCreaNuovoContattoSCURO non caricata");
        }
        btnCreaNuovoContatto.setFocusable(false);

        //Modifica del JButton ********* TESTING *********
        try {
            Image imgEliminaContatto = ImageIO.read(new File("Immagini/imgEliminaContatto.png"));     //STESSO DISCORSO
            btnEliminaContatto.setIcon(new ImageIcon(imgEliminaContatto));
        } catch (Exception e) {
            System.out.println("Icona btnCreaNuovoContattoSCURO non caricata");
        }

        textureTasti();
    }


    //TEMA CHIARO
    public void TemaChiaro() {

        FlatLightLaf.setup();
        SwingUtilities.updateComponentTreeUI(homepage);     //Serve a fare il refresh dei componenti a runtime (UTILE PER DARK MODE TO LIGHT)


        try {
            Image imgSwitchTema = ImageIO.read(new File("Immagini/imgTemaChiaro.png")); //Da settare la dimensione (PRESI DI MISURA GIUSTA COSI DA NON
            btnSwitchTema.setIcon(new ImageIcon(imgSwitchTema));
        } catch (Exception e) {                                                                                //AVERE NECESSITA' PER IL MOMENTO)
            System.out.println("Icona btnTemaChiaroCHIARO non caricata");
        }
        btnSwitchTema.setFocusable(false);

        //Modifica del JButton ********* TESTING **********
        try {
            Image imgCreaNuovoContatto = ImageIO.read(new File("Immagini/imgCreaNuovoContattoCHIARO.png")); //Da settare la dimensione (PRESI DI MISURA GIUSTA COSI DA NON
            btnCreaNuovoContatto.setIcon(new ImageIcon(imgCreaNuovoContatto));
        } catch (Exception e) {                                                                                //AVERE NECESSITA' PER IL MOMENTO)
            System.out.println("Icona btnCreaNuovoContattoCHIARO non caricata");
        }
        btnCreaNuovoContatto.setFocusable(false);

        //Modifica del JButton ********* TESTING **********
        try {
            Image imgEliminaContatto = ImageIO.read(new File("Immagini/imgEliminaContattoCHIARO.png"));     //STESSO DISCORSO
            btnEliminaContatto.setIcon(new ImageIcon(imgEliminaContatto));
        } catch (Exception e) {
            System.out.println("Icona btnEliminaContattoCHIARO non caricata");
        }

        textureTasti();
    }

    public void textureTasti(){
        btnEliminaContatto.setFocusable(false);

        lineeApertura.setFocusable(false);
        lineeChiusura.setFocusable(false);
    }

    //GETTER SETTER

    public static ArrayList<Contatti> getContattiDB() {
        return contattiDB;
    }

    public static void setContattiDB(ArrayList<Contatti> contattiDB) {
        Homepage.contattiDB = contattiDB;
    }

    public JButton getLineeApertura() {
        return lineeApertura;
    }

    public void setLineeApertura(JButton lineeApertura) {
        this.lineeApertura = lineeApertura;
    }

    public JButton getLineeChiusura() {
        return lineeApertura;
    }

    public void setLineeChiusura(JButton lineeChiusura) {
        this.lineeApertura = lineeChiusura;
    }

    public JPanel getPaneTendina() {
        return paneTendina;
    }

    public void setPaneTendina(JPanel paneTendina) {
        this.paneTendina = paneTendina;
    }

    public JButton getStampaListaContattiButton() {
        return stampaListaContattiButton;
    }

    public void setStampaListaContattiButton(JButton stampaListaContattiButton) {
        this.stampaListaContattiButton = stampaListaContattiButton;
    }

    public JScrollPane getJScrollBarListaContatti() {
        return JScrollBarListaContatti;
    }

    public void setJScrollBarListaContatti(JScrollPane JScrollBarListaContatti) {
        this.JScrollBarListaContatti = JScrollBarListaContatti;
    }

    public JPanel getPaneBase() {
        return paneBase;
    }

    public void setPaneBase(JPanel paneBase) {
        this.paneBase = paneBase;
    }

    public JPanel getCardHomepage() {
        return cardHomepage;
    }

    public void setCardHomepage(JPanel cardHomepage) {
        this.cardHomepage = cardHomepage;
    }

    public JButton getBtnCreaNuovoContatto() {
        return btnCreaNuovoContatto;
    }

    public void setBtnCreaNuovoContatto(JButton btnCreaNuovoContatto) {
        this.btnCreaNuovoContatto = btnCreaNuovoContatto;
    }

    public JPanel getHomepage() {
        return homepage;
    }

    public void setHomepage(JPanel homepage) {
        this.homepage = homepage;
    }

    public JButton getBtnEliminaContatto() {
        return btnEliminaContatto;
    }

    public void setBtnEliminaContatto(JButton btnEliminaContatto) {
        this.btnEliminaContatto = btnEliminaContatto;
    }

    public JButton getBtnSwitchTema() {
        return btnSwitchTema;
    }

    public void setBtnSwitchTema(JButton btnSwitchTema) {
        this.btnSwitchTema = btnSwitchTema;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }


}


