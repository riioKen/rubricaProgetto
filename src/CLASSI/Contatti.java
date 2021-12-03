package CLASSI;

public class Contatti {
    //ATTRIBUTI
    String nome; //Il nome del contatto
    String cognome; //Il cognome del contatto
    String foto; //Ragionare su come inserire la foto
    String email; //L'indirizzo email del contatto
    String indirizzo; //L'indirizzo di casa del contatto
    String cellulare; //Il numero di celullare del contatto
    String fisso; //Il numero di telefono fisso del contatto

    Controller control;

    //COSTRUTTORE
    public Contatti(Controller controller) {
        control.controller = new controller();
    }
}
