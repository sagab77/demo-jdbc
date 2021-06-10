package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDaoJdbc2 implements FournisseurDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;

    private void setConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
        } catch (SQLException e) {
            System.err.println("Erreur de connection à la BDD");
        }
    }

    private void closeDb() {
        try {
            if (connection != null)
                connection.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (result != null)
                result.close();
        } catch (SQLException e) {
            System.err.println("Erreur fermeture des resources d'accès à la BDD");
        }
    }

    @Override
    public List<Fournisseur> extraire() {

        List<Fournisseur> listeFournisseurs = new ArrayList<>();
        setConnection();


        try {
            preparedStatement = connection.prepareStatement("Select * from fournisseur");
            result = preparedStatement.executeQuery();

            while (result.next()) {
                int id = result.getInt("ID");
                String nom = result.getString("NOM");
                listeFournisseurs.add(new Fournisseur(id, nom));

            }
            return listeFournisseurs;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            closeDb();
        }
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        setConnection();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO fournisseur (ID, NOM) values (?, ?);");
            preparedStatement.setInt(1, fournisseur.getId());
            preparedStatement.setString(2, fournisseur.getNom());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeDb();
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        int result = 0;
        setConnection();

        try {

            preparedStatement = connection.prepareStatement("UPDATE fournisseur SET NOM=? WHERE NOM=?");
            preparedStatement.setString(1, nouveauNom);
            preparedStatement.setString(2, ancienNom);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeDb();
            return result;
        }

    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        int result = 0;
        setConnection();


        try {
            preparedStatement = connection.prepareStatement("DELETE FROM fournisseur WHERE NOM=?");
            preparedStatement.setString(5, fournisseur.getNom());
            result = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeDb();
            if (result != 0)
                return true;
            else
                return false;


        }

    }

}
