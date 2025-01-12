package fr.pantheonsorbonne.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Soldat {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String categorie;
    private String etatdesante;
    private String galacticRegistrationNumberS;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEtatdesante() {
        return etatdesante;
    }

    public void setEtatdesante(String etatdesante) {
        this.etatdesante = etatdesante;
    }

    public String getGalacticRegistrationNumberS() {
        return galacticRegistrationNumberS;
    }

    public void setGalacticRegistrationNumberS(String galacticRegistrationNumberS) {
        this.galacticRegistrationNumberS = galacticRegistrationNumberS;
    }
}
