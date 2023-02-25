package DAO;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AggiornamentoContattoDAO {


    void aggiornaContatto(Contatti contatto, Indirizzo indirizzo, Contatti contattoNew,Indirizzo indirizzo_new, ArrayList<Indirizzo> indirizzoVecchio, ArrayList<String> arrayTxtIndirizzo);
}
