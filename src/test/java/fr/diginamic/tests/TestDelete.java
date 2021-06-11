package fr.diginamic.tests;

import org.mariadb.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {
    public static void main(String[] args) {
        Connection connection = null;
        Statement state = null;

        try {
            // 1- Load mariaDb Driver
            DriverManager.registerDriver(new Driver());
            // 2- Connect to the database 'compta'
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");

            state = connection.createStatement();
            int res = state.executeUpdate("DELETE FROM fournisseur WHERE NOM='La Maison des Peintures';");

            if (res!=0){
                System.out.println("DELETE succeed !");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (connection!=null)
                    connection.close(); // Indispensable pour certaines BDD. Exemple : Oracle
                if (state!=null)
                    state.close(); // Indispensable pour certaines BDD. Exemple : Oracle

            } catch (SQLException throwables){
                System.err.println("Erreur lors de la fermeture des resources");
                throwables.printStackTrace();
            }
        }
    }
}

