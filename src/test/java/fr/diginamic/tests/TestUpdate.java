package fr.diginamic.tests;

import org.mariadb.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement state = null;

        try {
            // 1- Load mariaDb Driver
            DriverManager.registerDriver(new Driver());
            // 2- Connect to the database 'compta'
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");

            state = connection.createStatement();
            int res = state.executeUpdate("UPDATE fournisseur SET NOM='La Maison des Peintures' WHERE NOM='La Maison de la Peinture';");

            if (res!=0){
                System.out.println("UPDATE succeed !");
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

