package GUI;

import Controller.*;
import CLASSI.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Homepage extends JFrame {
    //ATTRIBUTI
    private JLabel lbNomeRubrica;
    private JButton btnCreaContatto;
    private JPanel homepage;
    Controller control;


    public Homepage(Controller controller) {
        control = controller; //Serve a linkare il controller al JPanel
        setTitle("Homepage");
        setContentPane(homepage);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Serve a terminare il programma quando si preme la X
        setLocation(300,300);

        //Modifica del JButton ********* TESTING **********
        /*try {
            Image image = ImageIO.read(new File("Immagini/contact.png")).getScaledInstance(50,50,Image.SCALE_DEFAULT);
            btnCreaContatto.setIcon(new ImageIcon(image));
        }
        catch(Exception e){
            System.out.println("Icona btnCreaContatto non caricata");
        }*/
    }
}
