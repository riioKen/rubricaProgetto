package Controller;

import Classi.*;
import Gui.*;

import javax.swing.*;
import java.sql.SQLException;


public class Controller{

    Contatti contatti;
    Messaging messaging;
    GruppoContatti gruppoContatti;


    Homepage homepage;
    CreaContatto creaContatto;
    SchedaInfoContatto infoContatto;
    SchedaRicerche schedaRicerche;

    //MAIN
    public static void main(String[] args) throws SQLException {
        Controller controller = new Controller();
    }

    //COSTRUTTORE
    public Controller() throws SQLException {

        homepage = new Homepage(this); //Serve a creare l'oggetto della GUI
        creaContatto = new CreaContatto(this);
        infoContatto = new SchedaInfoContatto(this);
        schedaRicerche = new SchedaRicerche(this);
        homepage.setVisible(true);

    }

    //METODI

    public void switchJPanel(JPanel accendi){
        homepage.getCardHomepage().removeAll();

        homepage.getCardHomepage().add(accendi);

        homepage.getCardHomepage().repaint();
        homepage.getCardHomepage().revalidate();

    }

    public void switchJPanelInView(JPanel accendi){
        homepage.getJScrollBarListaContatti().setViewportView(accendi);    //TESTING

    }


    //GETTER SETTER


    public SchedaRicerche getSchedaRicerche() {
        return schedaRicerche;
    }

    public void setSchedaRicerche(SchedaRicerche schedaRicerche) {
        this.schedaRicerche = schedaRicerche;
    }

    public SchedaInfoContatto getInfoContatto() {
        return infoContatto;
    }

    public void setInfoContatto(SchedaInfoContatto infoContatto) {
        this.infoContatto = infoContatto;
    }

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
