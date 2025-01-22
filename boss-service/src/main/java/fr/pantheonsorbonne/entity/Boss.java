package fr.pantheonsorbonne.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Boss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nom;
    private String galacticRegistrationNumberBoss;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getGalacticRegistrationNumberBoss() {
        return galacticRegistrationNumberBoss;
    }

    public void setGalacticRegistrationNumberBoss(String galacticRegistrationNumberBoss) {
        this.galacticRegistrationNumberBoss = galacticRegistrationNumberBoss;
    }
}
