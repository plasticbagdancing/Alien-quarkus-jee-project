package fr.pantheonsorbonne.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "champ_de_bataille")
<<<<<<< HEAD
public class ChampDeBataille {

=======

public class ChampDeBataille {
>>>>>>> b944f671ba76cc20ba9e071eda0b18899a79cb3e
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String etat;

    @Column(name = "nb_mort", nullable = false)
    private int nombreMorts;

    @Column(name = "nb_blesse", nullable = false)
    private int nombreBlesses;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getNombreMorts() {
        return nombreMorts;
    }

    public void setNombreMorts(int nombreMorts) {
        this.nombreMorts = nombreMorts;
    }

    public int getNombreBlesses() {
        return nombreBlesses;
    }

    public void setNombreBlesses(int nombreBlesses) {
        this.nombreBlesses = nombreBlesses;
<<<<<<< HEAD
    }
=======
>>>>>>> b944f671ba76cc20ba9e071eda0b18899a79cb3e
}
}

