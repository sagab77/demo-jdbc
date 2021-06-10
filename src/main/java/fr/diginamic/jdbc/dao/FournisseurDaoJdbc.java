package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.List;

public interface FournisseurDaoJdbc extends FournisseurDao {

    @Override
    List<Fournisseur> extraire();


    @Override
    void insert(Fournisseur fournisseur);

    @Override
    int update(String ancienNom, String nouveauNom);

    @Override
    boolean delete(Fournisseur fournisseur);


}
