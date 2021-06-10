package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;
import org.mariadb.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDaoJdbc {

    public static void main(String[] args) {


        FournisseurDao fournisseurDao = new FournisseurDaoJdbc();
        Fournisseur fournisseur = new Fournisseur(6,"France de matériaux");
        fournisseurDao.insert(fournisseur);
        fournisseurDao.extraire();
        fournisseurDao.update("France de matériaux","France matériaux");
        fournisseurDao.extraire();
        fournisseurDao.delete(fournisseur);
        Fournisseur fournisseur1 = new Fournisseur(7,"France d'Imports");
        fournisseurDao.insert(fournisseur1);



    }




    }








