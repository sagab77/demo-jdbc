package fr.diginamic.jdbc;

import org.mariadb.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 */

public class TestDelete {
    public static void main(String[] args) throws SQLException {

        DriverManager.registerDriver(new Driver());


        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");


        Statement statement = connection.createStatement();

        int nb = statement.executeUpdate("DELETE FROM COMPTA.FOURNISSEUR WHERE NOM='La Maison des Peintures'");
        System.out.println("Nombre de lignees suprimees " + nb);

        statement.close();
        connection.close();



    }
}
