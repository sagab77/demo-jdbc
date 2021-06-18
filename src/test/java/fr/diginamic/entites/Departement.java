package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

public class Departement extends Country {


    private Region region;
    private Pays pays;

    public Departement(String code, String nom, Region region, Pays pays) {
        super(nom, code);
        this.region = region;
        this.pays = pays;
        this.pays.ajoutDep(this);
    }

    // méthode d'ajout d'une ville aux attributs de mon département
    public void ajoutVille(Ville ville) {
        this.population += ville.population;
        this.listeVille.add(ville);
    }

}





