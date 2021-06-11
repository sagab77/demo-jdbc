package fr.diginamic.prepared;

import dao.FournisseurDao;
import fr.diginamic.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDaoJdbcPrepared implements FournisseurDao {
    Connection connection = null;
    PreparedStatement prepStatement = null;
    ResultSet cursor = null;

    private void initConnection(){
        try {
            // 1- Load mariaDb Driver
            DriverManager.registerDriver(new Driver());
            // 2- Connect to the database 'compta'
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
        } catch (SQLException e){
            System.err.println("Erreur de connection à la BDD");
        }
    }

    private void closeBddAccess(){
        try {
            if (connection!=null)
                connection.close(); // Indispensable pour certaines BDD. Exemple : Oracle
            if (prepStatement!=null)
                prepStatement.close(); // Indispensable pour certaines BDD. Exemple : Oracle
            if(cursor!=null)
                cursor.close();
        } catch (SQLException e){
            System.err.println("Erreur lors de la fermeture des resources d'accès à la BDD");
        }
    }

    @Override
    public List<Fournisseur> extraire() {
        List<Fournisseur> listeFournisseurs = new ArrayList<>();
        initConnection();

        try {
            prepStatement = connection.prepareStatement("SELECT * FROM fournisseur");
            cursor = prepStatement.executeQuery();

            while (cursor.next()){
                int id = cursor.getInt("ID");
                String nom = cursor.getString("NOM");
                listeFournisseurs.add(new Fournisseur(id, nom));
            }

            return listeFournisseurs;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            closeBddAccess();
        }
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        initConnection();
        try {
            prepStatement = connection.prepareStatement("INSERT INTO fournisseur (ID, NOM) values (?, ?);");
            prepStatement.setInt(1, fournisseur.getId());
            prepStatement.setString(2, fournisseur.getNom());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeBddAccess();
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        int res = 0;
        initConnection();
        try {
            prepStatement = connection.prepareStatement("UPDATE fournisseur SET NOM=? WHERE NOM=?;");
            prepStatement.setString(1, nouveauNom);
            prepStatement.setString(2, ancienNom);
            res = prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeBddAccess();
            return res;
        }
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        int res = 0;
        initConnection();
        try {
            prepStatement = connection.prepareStatement("DELETE FROM fournisseur WHERE NOM=?;");
            prepStatement.setString(1, fournisseur.getNom());
            res = prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            closeBddAccess();
            if(res!=0)
                return true;
            else
                return false;
        }
    }
}
