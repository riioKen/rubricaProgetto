package GUI;

import Controller.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class Homepage extends JFrame {

    //ATTRIBUTI
    private JButton btnCreaNuovoContatto;
    private JPanel homepage;
    private JButton btnEliminaContatto;
    private JButton btnSwitchTema;

    private Timer timer;

    Controller control;



    public Homepage(Controller controller) {

        control = controller; //Serve a linkare il controller al JPanel
        setTitle("Homepage");
        setContentPane(homepage);
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Serve a terminare il programma quando si preme la X
        setLocation(300, 300);


        TemaScuro();  //INIZIALIZAZZIONE DEL TEMA SCURO BY DEFAULT


        //TIMER CHE FA DA SWITCH PER LA DARK MODE E LA LIGHT MODE
        btnSwitchTema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        btnCreaNuovoContatto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.cambioFinestra(control.getHomepage(), control.getCreaContatto());
            }
        });
    }


    //TEMA SCURO SET BY DEFAULT
    public void TemaScuro(){        //SOLO TEMA SCURO: CAMBIARE LE ICONE. Attuali 24px Cambiare a quelle da 32px
        FlatDarculaLaf.setup();
        SwingUtilities.updateComponentTreeUI(homepage);

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

        //Modifica del JButton ********* TESTING **********
        try {
            Image imgEliminaContatto = ImageIO.read(new File("Immagini/imgEliminaContatto.png"));     //STESSO DISCORSO
            btnEliminaContatto.setIcon(new ImageIcon(imgEliminaContatto));
        } catch (Exception e) {
            System.out.println("Icona btnCreaNuovoContattoSCURO non caricata");
        }
        btnEliminaContatto.setFocusable(false);
    }

    //TEMA CHIARO
    public void TemaChiaro(){


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

        btnEliminaContatto.setFocusable(false);
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


