package DAO;

import Classi.Contatti;

import java.sql.SQLException;

public interface CercaInfoContattoDAO {
    Contatti cercaInfoContatti(String nome) throws SQLException;
}
