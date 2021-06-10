package fr.diginamic.jdbc;

import org.mariadb.jdbc.Driver;

import java.sql.*;

public class TestInsertion {

    public static void main(String[] args) throws SQLException {

        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");

        Statement statement = connection.createStatement();


        int nb = statement.executeUpdate("INSERT into FOURNISSEUR (ID,NOM) VALUES (4,'La Maison de la Peinture ')");
        if (nb != 0) {
            System.out.println("L'insertion s'est bien passée.");
        }


            connection.close();


        }
    }

