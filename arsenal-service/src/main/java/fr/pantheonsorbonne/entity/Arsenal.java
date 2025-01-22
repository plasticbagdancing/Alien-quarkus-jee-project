package fr.pantheonsorbonne.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Arsenal {

    @Id
    @GeneratedValue
    private Long id;
    private String Type;
    private Integer duration;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
