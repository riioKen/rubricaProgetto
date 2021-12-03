package GUI;

import Controller.*;
import CLASSI.*;

import javax.swing.*;


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

    }
}
