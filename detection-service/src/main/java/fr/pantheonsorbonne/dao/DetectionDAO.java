package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Detection;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DetectionDAO {

    private final List<Detection> database = new ArrayList<>();

    public void save(Detection detection) {
        database.add(detection);
        System.out.println("Detection saved: " + detection.getMessage());
    }

    public List<Detection> findAll() {
        return database;
    }
}
