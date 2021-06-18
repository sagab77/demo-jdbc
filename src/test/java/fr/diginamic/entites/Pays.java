package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

public class Pays extends Country{

    private List<Country> listeDep 	= new ArrayList<>();
    private List<Country> listeRegion 	= new ArrayList<>();


    public Pays(String code) {
        super(code, code);
    }

    public void ajoutVille(Ville ville) {
        this.population += ville.population;
        this.listeVille.add(ville);
    }

    public void ajoutDep(Departement dep) {
        this.listeDep.add(dep);
    }

    public void ajoutRegion(Region region) {
        this.listeRegion.add(region);
    }

    public List<Country> getListeDep() {
        return listeDep;
    }

    public List<Country> getListeRegion() {
        return listeRegion;
    }




}

