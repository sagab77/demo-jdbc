package dao;

import fr.diginamic.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDaoJdbc implements FournisseurDao {
    Connection connection = null;
    Statement state = null;
    ResultSet cursor = null;

    private void initConnection(){
        try {
            // 1- Load mariaDb Driver
            DriverManager.registerDriver(new Driver());
            // 2- Connect to the database 'compta'
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
            state = connection.createStatement();
        } catch (SQLException e){
            System.err.println("Erreur de connection à la BDD");
        }
    }

    private void closeBddAccess(){
        try {
            if (connection!=null)
                connection.close();
            if (state!=null)
                state.close();
            if(cursor!=null)
                cursor.close();
        } catch (SQLException throwables){
            System.err.println("Erreur lors de la fermeture des resources d'accès à la BDD");
        }
    }

    @Override
    public List<Fournisseur> extraire() {
        List<Fournisseur> listeFournisseurs = new ArrayList<>();
        initConnection();

        try {
            cursor = state.executeQuery("SELECT * FROM fournisseur");

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
            int res = state.executeUpdate("INSERT INTO fournisseur (ID, NOM) values ('"+fournisseur.getId()+"', '"+fournisseur.getNom()+"');");
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
            res = state.executeUpdate("UPDATE fournisseur SET NOM='"+nouveauNom+"' WHERE NOM='"+ancienNom+"';");
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
            res = state.executeUpdate("DELETE FROM fournisseur WHERE NOM='"+fournisseur.getNom()+"';");
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
