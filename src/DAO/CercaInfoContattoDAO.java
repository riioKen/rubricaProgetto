package DAO;

import Classi.Contatti;

import java.sql.SQLException;

public interface CercaInfoContattoDAO {
    public Contatti cercaInfoContatti(String nome) throws SQLException;
}
