package SQL;

import Controller.Controller;
import java.sql.*;


public class Connessione {
    Controller control;

    public Connessione(Controller controller){
        control = controller;

        try{
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/Rubrica";

            Connection conn = DriverManager.getConnection(url, "postgres", "rubrica");
            System.out.println("Connessione OK");

            conn.close();
        }
        catch(ClassNotFoundException e){
            System.out.println("DB Driver non trovato");
            System.out.println(e);
        }
        catch(SQLException e){
            System.out.println("Connessione fallita");
            System.out.println(e);
        }
    }
}