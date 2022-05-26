package DAO;

import Classi.Contatti;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StampaContattoDAO {

    ArrayList<Contatti> stampaContatti() throws SQLException;

}
