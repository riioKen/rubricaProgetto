package DAO;

import Classi.Contatti;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AggiornamentoContattoDAO {

    void aggiornaContatto(Contatti contatto, Contatti contatto_new, ArrayList<String> indirizziVecchio, ArrayList<String> txtIndirizzi, ArrayList<String> emailVecchio, ArrayList<String> txtEmail) throws SQLException;

}
