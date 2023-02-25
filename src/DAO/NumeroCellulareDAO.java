package DAO;

import Model.NumeroCellulare;

import java.sql.SQLException;

public interface NumeroCellulareDAO {
    void inserisciCellulare(int id, String cellulare) throws SQLException;
    void modificaCellulare(int id, NumeroCellulare cellulare, String cellulareNew) throws SQLException;

    NumeroCellulare getCellulare(int id) throws SQLException;

}
