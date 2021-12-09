package Controller;

import CLASSI.*;
import GUI.*;

import javax.swing.*;

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
    public void cambioFinestra(JFrame spegni, JFrame accendi){
        spegni.setVisible(false);
        accendi.setVisible(true);
    }



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
