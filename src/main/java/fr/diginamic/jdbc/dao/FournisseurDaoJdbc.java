package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDaoJdbc implements FournisseurDao {
    Connection connection = null;
    Statement stat = null;
    ResultSet curseur = null;

    @Override

    public List<Fournisseur> extraire() {
        Connection connection = null;
        Statement stat = null;
        ResultSet curseur = null;

        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
            stat = connection.createStatement();
            curseur = stat.executeQuery("SELECT * FROM FOURNISSEUR ");

            ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
            while (curseur.next()) {
                int id = curseur.getInt("ID");
                String nom = curseur.getString("NOM");

                Fournisseur fournisseurCourant = new Fournisseur(id, nom);
                fournisseurs.add(fournisseurCourant);

            }
            for (Fournisseur fournisseur : fournisseurs) {
                System.out.println(fournisseur);
            }
            System.out.println(connection);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (curseur != null) {
                    curseur.close();
                }
                stat.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


        return null;
    }


    public void insert(Fournisseur fournisseur) {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
            System.out.println(connection);
            Statement stat = connection.createStatement();
            stat.executeUpdate("INSERT INTO FOURNISSEUR (ID,NOM) VALUES ('"+ fournisseur.getId() +"','"+fournisseur.getNom()+"')");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int update(String ancienNom, String nouveauNom) {
        Connection connection = null;
        Statement stat = null;
        int nb = 0;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
            System.out.println(connection);
            stat = connection.createStatement();
            nb = stat.executeUpdate("UPDATE FOURNISSEUR SET NOM='" + nouveauNom + "' WHERE NOM ='" + ancienNom + "'");
            //UPDATE FOURNISSEUR SET NOM = 'le nouveaunom' WHERE NOM =' ancienNom';

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {

            try {
                stat.close();
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }
        return nb;
    }

    public boolean delete(Fournisseur fournisseur) {

        Connection connection = null;
        Statement stat = null;

        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
            System.out.println(connection);
            stat = connection.createStatement();
            stat.executeUpdate("DELETE FROM FOURNISSEUR WHERE ID =" + fournisseur.getId());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {

            try {
                stat.close();
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }
        return true;
    }

}