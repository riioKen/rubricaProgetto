package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.NumeroCellulareDAO;
import Model.NumeroCellulare;

import java.sql.*;

public class NumeroCellularePostgreSQL implements NumeroCellulareDAO {

    private Connection conn;

    public NumeroCellularePostgreSQL() throws SQLException {
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe NumeroCellularePostgreSQL");
            e.printStackTrace();
        }
    }

    @Override
    public void inserisciCellulare(int id, String cellulare) throws SQLException {
        String inserisciCellulare = "INSERT INTO NumeroCellulare(cellulare, idcontatto) VALUES ('"+cellulare+"' , '"+id+"')";

        PreparedStatement st = conn.prepareStatement(inserisciCellulare);
        st.executeUpdate();
    }

    @Override
    public void modificaCellulare(int id, NumeroCellulare cellulare, String cellulareNew) throws SQLException {

        String updateCellulare = "UPDATE NumeroCellulare SET cellulare = '"+cellulareNew+"' WHERE cellulare = '"+cellulare.getNumeroCellulare()+"'";

        PreparedStatement st = conn.prepareStatement(updateCellulare);
        st.executeUpdate();
    }

    @Override
    public NumeroCellulare getCellulare(int id) throws SQLException {

        System.out.println(id);
        String getCellulare = ("SELECT cellulare FROM numeroCellulare WHERE idContatto = "+id+"");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(getCellulare);

        NumeroCellulare cellulare = new NumeroCellulare();
        while(rs.next()){
            cellulare.setNumeroCellulare(rs.getString("cellulare"));
            //cellulare.setId(rs.getLong(id));
        }
        System.out.println(cellulare);
        return cellulare;
    }


}
