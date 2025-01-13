package fr.pantheonsorbonne.entity;

import java.time.LocalDateTime;

public class Detection {
    private int idAlerte;
    private LocalDateTime datetime;
    private int idRadar;
    private int nbAliens;
    private String message;

    // Getters et Setters
    public int getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(int idAlerte) {
        this.idAlerte = idAlerte;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getIdRadar() {
        return idRadar;
    }

    public void setIdRadar(int idRadar) {
        this.idRadar = idRadar;
    }

    public int getNbAliens() {
        return nbAliens;
    }

    public void setNbAliens(int nbAliens) {
        this.nbAliens = nbAliens;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
