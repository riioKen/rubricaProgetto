package CLASSI;

public class GruppoContatti {
    //ATTRIBUTI
    String nomeGruppo; //Il nome del gruppo di contatti
    int numeroContatti; //Il numero di contatti presenti nel gruppo

    Controler control;

    //COSTRUTTORE
    public GruppoContatti(Controller controller) {
        control.controller = new controller();
    }
}
