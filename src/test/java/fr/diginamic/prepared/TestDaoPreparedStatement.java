package fr.diginamic.prepared;

import dao.FournisseurDaoJdbc;
import fr.diginamic.entites.Fournisseur;

import java.util.List;

public class TestDaoPreparedStatement {
    public static void main(String[] args) {
        FournisseurDaoJdbc fournisseurDao = new FournisseurDaoJdbc();

        // Insert nouveau fournisseur
        fournisseurDao.insert(new Fournisseur(5, "France de matériaux"));

        // Extraction et affichage fournisseurs BDD
        List<Fournisseur> listeFournisseurs = fournisseurDao.extraire();
        for (Fournisseur f : listeFournisseurs) {
            System.out.println(f.toString());
        }

        // Update d'un fournisseur
        int result = fournisseurDao.update("France de matériaux", "France matériaux");
        if(result!=0)
            System.out.println("UPDATE succeed");
        // Affichage
        listeFournisseurs = fournisseurDao.extraire();
        for (Fournisseur f : listeFournisseurs) {
            System.out.println(f.toString());
        }

        // Delete d'un fournisseur
        boolean isSuccessfull = fournisseurDao.delete(new Fournisseur(5, "France matériaux"));
        if (isSuccessfull)
            System.out.println("DELETE SUCCEED");

        // Affichage
        listeFournisseurs = fournisseurDao.extraire();
        for (Fournisseur f : listeFournisseurs) {
            System.out.println(f.toString());
        }

    }
}
