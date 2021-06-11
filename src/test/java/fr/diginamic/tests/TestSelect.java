package fr.diginamic.tests;

import fr.diginamic.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;

public class TestSelect {
    public static void main(String[] args) {
        Connection connection = null;
        Statement state = null;
        ResultSet cursor = null;

        try {
            // 1- Load mariaDb Driver
            DriverManager.registerDriver(new Driver());
            // 2- Connect to the database 'compta'
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");

            state = connection.createStatement();
            cursor = state.executeQuery("SELECT * FROM fournisseur");

            ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();

            while (cursor.next()) {
                int id = cursor.getInt("ID");
                String nom = cursor.getString("NOM");
                listeFournisseurs.add(new Fournisseur(id, nom));
            }

            for (Fournisseur f : listeFournisseurs) {
                System.out.println(f.toString());
            }
            cursor.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (state != null)
                    state.close();
                if (cursor != null)
                    cursor.close();
            } catch (SQLException throwables) {
                System.err.println("Erreur lors de la fermeture des resources d'accès à la BDD");
            }
        }
    }
}
