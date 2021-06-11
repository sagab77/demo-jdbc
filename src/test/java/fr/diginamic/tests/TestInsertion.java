package fr.diginamic.tests;

import org.mariadb.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsertion {
    public static void main(String[] args) throws SQLException {
        // 1- Load mariaDb Driver
        DriverManager.registerDriver(new Driver());

        // 2- Connect to the database 'compta'
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");

        Statement state = connection.createStatement();
        int res = state.executeUpdate("INSERT INTO fournisseur (ID,NOM) values (10,'La Maison de la Peinture');");

        if (res!=0){
            System.out.println("Insertion succeed !");
        }

        state.close(); // Indispensable pour certaines BDD. Exemple : Oracle
        connection.close(); // Indispensable pour certaines BDD. Exemple : Oracle
    }
}