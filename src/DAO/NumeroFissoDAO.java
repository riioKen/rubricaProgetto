package DAO;

import Model.NumeroFisso;

import java.sql.SQLException;

public interface NumeroFissoDAO {
    void inserisciFisso(int id, String fisso) throws SQLException;
    void modificaFisso(int id, NumeroFisso fisso, String fissoNew) throws SQLException;

    NumeroFisso getFisso(int id) throws SQLException;
}
