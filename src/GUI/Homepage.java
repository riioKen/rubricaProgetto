package GUI;

import Controller.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Homepage extends JFrame {
    private JButton btnCreaNuovoContatto;
    private JPanel homepage;
    private JButton btnEliminaContatto;
    Controller control;


    public Homepage(Controller controller) {
        control = controller; //Serve a linkare il controller al JPanel
        setTitle("Homepage");
        setContentPane(homepage);
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Serve a terminare il programma quando si preme la X
        setLocation(300, 300);

        //Modifica del JButton ********* TESTING **********
        try {
            Image imgCreaNuovoContatto = ImageIO.read(new File("Immagini/imgCreaNuovoContatto.png"));
            btnCreaNuovoContatto.setIcon(new ImageIcon(imgCreaNuovoContatto));
        } catch (Exception e) {
            System.out.println("Icona btnCreaNuovoContatto non caricata");
        }

        //Modifica del JButton ********* TESTING **********
        try {
            Image imgEliminaContatto = ImageIO.read(new File("Immagini/imgEliminaContatto.png"));
            btnEliminaContatto.setIcon(new ImageIcon(imgEliminaContatto));
        } catch (Exception e) {
            System.out.println("Icona btnCreaNuovoContatto non caricata");
        }
    }
}


