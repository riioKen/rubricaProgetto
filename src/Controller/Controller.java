package Controller;

import CLASSI.*;
import GUI.*;

import javax.swing.*;

public class Controller{
    //ATTRIBUTI
    Homepage home;

    Contatti contatti;
    Messaging messaging;
    GruppoContatti gruppoContatti;


    //MAIN
    public static void main(String[] args){
        Controller controller = new Controller();
    }

    //COSTRUTTORE
    public Controller(){
        home = new Homepage(this); //Serve a creare l'oggetto della GUI


        home.setVisible(true);
    }

    //GETTER SETTER
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
