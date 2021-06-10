package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.List;

public class TestDaoJdbc2 {
    public static void main(String[] args) {

        FournisseurDao fournisseurDao = new FournisseurDaoJdbc();

        // insert un nouveau fournisseur
        fournisseurDao.insert(new Fournisseur(7,"Castorama"));

        // affichage fournisseurs BD

        List<Fournisseur> listeFournisseurs = fournisseurDao.extraire();

        for (Fournisseur f:listeFournisseurs) {
            System.out.println(f.toString());

        }

        // update fournisseur

        int result = fournisseurDao.update("France de Materiaux","France matériaux");
        if(result!=0) {
            System.out.println("Update ok");
            listeFournisseurs = fournisseurDao.extraire();
            for (Fournisseur f:listeFournisseurs) {
                System.out.println(f.toString());

            }

            // delete fournisseur

            boolean deleteOk = fournisseurDao.delete(new Fournisseur(5,"France materiaux"));
            if(deleteOk)
                System.out.println("Delete OK");

            // affichage

            listeFournisseurs = fournisseurDao.extraire();
            for (Fournisseur f: listeFournisseurs) {
                System.out.println(f.toString());

            }
        }



    }
}
