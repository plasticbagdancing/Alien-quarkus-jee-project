package fr.pantheonsorbonne.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Medecin {

    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String specialite;
    private String galacticRegistrationNumberM;

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

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getGalacticRegistrationNumberM() {
        return galacticRegistrationNumberM;
    }

    public void setGalacticRegistrationNumberM(String galacticRegistrationNumberM) {
        this.galacticRegistrationNumberM = galacticRegistrationNumberM;
    }
}
