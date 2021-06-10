package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class TestDaoJdbc implements FournisseurDaoJdbc {

    public static void main(String[] args) throws SQLException {

        ArrayList<FournisseurDao> listeFournisseurs = new ArrayList<>();




        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");

        Statement statement = connection.createStatement();


        /*int nb = statement.executeUpdate("INSERT into FOURNISSEUR (ID,NOM) VALUES (6,'France de matériaux')");
        if (nb != 0) {
            System.out.println("L'insertion s'est bien passée.");
        }*/

        ResultSet result = statement.executeQuery("Select * from fournisseur");

        while(result.next()) {
            int id = result.getInt("id");
            String nom = result.getString("nom");





        }


        }

    @Override
    public List<Fournisseur> extraire() {
        return null;
    }

    @Override
    public void insert(Fournisseur fournisseur) {

    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        return 0;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        return false;
    }
}

