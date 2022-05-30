package DAO;

import Classi.Contatti;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CercaInfoContattoDAO {
    Contatti cercaInfoContatti(String nome, ArrayList<String> indirizzosecondario, ArrayList<String> emailsecondario) throws SQLException;
}
