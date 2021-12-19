package Controller;

import Classi.*;
import Gui.*;

import javax.swing.*;
import java.awt.*;

public class Controller{

    Contatti contatti;
    Messaging messaging;
    GruppoContatti gruppoContatti;


    Homepage homepage;
    CreaContatto creaContatto;


    //MAIN
    public static void main(String[] args){
        Controller controller = new Controller();
    }

    //COSTRUTTORE
    public Controller(){

        homepage = new Homepage(this); //Serve a creare l'oggetto della GUI
        creaContatto = new CreaContatto(this);
        homepage.setVisible(true);

    }

    //METODI

    public void switchJPanel(JPanel accendi){
        homepage.getCardHomepage().removeAll();

        homepage.getCardHomepage().add(accendi);

        homepage.getCardHomepage().repaint();
        homepage.getCardHomepage().revalidate();

        /*homepage.setContentPane(accendi);
        homepage.revalidate();*/

    }

    /*public void testCiclo(int i, int j){
        while(i  < CreaContatto.getInsContatti().size()){ //Scorro un ArrayList dove sono contenuti i dati di ogni contatto
            JPanel paneLista = new JPanel(); //L'intenzione è quella di creare dinamicamente un JPanel dove successivamente si andrà ad inserire il JButton collegato ad uno specifico elemento dell'ArrayList
            paneLista.setLayout(new GridLayout(0,1));
            JButton btnSchedaContatto = new JButton(); //Creazione del JButton che sarà poi collegato ad uno specifico elemento dell'ArrayList
            btnSchedaContatto.setText(CreaContatto.getInsContatti().get(i).getNome() +" "+ CreaContatto.getInsContatti().get(i).getCognome()); //Per il momento mi accontento solo di dargli queste informazioni al JButton
            paneLista.add(btnSchedaContatto); //Aggiungo il JButton al JPanel
            homepage.getPaneBase().setLayout(new GridLayout(j,0));
            homepage.getPaneBase().add(paneLista);
            i++;
            j++;
        }
    }*/



    //GETTER SETTER


    public CreaContatto getCreaContatto() {
        return creaContatto;
    }

    public void setCreaContatto(CreaContatto creaContatto) {
        this.creaContatto = creaContatto;
    }

    public Homepage getHomepage() {
        return homepage;
    }

    public void setHomepage(Homepage homepage) {
        this.homepage = homepage;
    }

    public Contatti getContatti() {
        return contatti;
    }

    public void setContatti(Contatti contatti) {
        this.contatti = contatti;
    }

    public Messaging getMessaging() {
        return messaging;
    }

    public void setMessaging(Messaging messaging) {
        this.messaging = messaging;
    }

    public GruppoContatti getGruppoContatti() {
        return gruppoContatti;
    }

    public void setGruppoContatti(GruppoContatti gruppoContatti) {
        this.gruppoContatti = gruppoContatti;
    }
}
