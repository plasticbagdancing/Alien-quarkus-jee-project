package fr.pantheonsorbonne.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Alien {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String galacticRegistrationNumber;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getGalacticRegistrationNumber() {
        return galacticRegistrationNumber;
    }
    public void setGalacticRegistrationNumber(String galacticRegistrationNumber) {
        this.galacticRegistrationNumber = galacticRegistrationNumber;
    }


}
