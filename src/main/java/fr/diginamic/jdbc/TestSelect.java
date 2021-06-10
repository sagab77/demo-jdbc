package fr.diginamic.jdbc;

import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;

public class TestSelect {
    public static void main(String[] args) {

        ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // chargement de driver
            DriverManager.registerDriver(new Driver());

            // creation de connexion
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");

            // operations select  database
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM FOURNISSEUR");
            while (resultSet.next()) {

                Integer id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");

                Fournisseur fournisseur = new Fournisseur(id, nom);
                listeFournisseurs.add(fournisseur);

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();

                }
                if(statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

                System.err.println("Erreur fermeture des ressources d'access a database: " + e.getMessage());
            }

        }

        for (Fournisseur fournisseur: listeFournisseurs) {

            System.out.println(fournisseur);

        }


    }
}
