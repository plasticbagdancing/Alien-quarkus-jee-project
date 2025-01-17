package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.DetectionDAO;
import fr.pantheonsorbonne.entity.Detection;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
@ApplicationScoped
public class DetectionService {

    @Inject
    DetectionDAO detectionDAO;

    @PostConstruct
    public void init() {
        if (detectionDAO == null) {
            System.err.println("DetectionDAO n'est pas injecté correctement !");
        } else {
            System.out.println("DetectionDAO est injecté avec succès.");
        }
    }

    public void handleDetection(Detection detection) {
        detectionDAO.save(detection);
        // Logique métier pour gérer les alertes critiques
        int seuilCritique = 100;
        if (detection.getNbAliens() > seuilCritique || detection.getMessage().contains("Type_alien_dangereux")) {
            System.out.println("CRITIQUE : Notifier le boss pour riposter.");
        } else {
            System.out.println("Pas critique : Envoyer une notification standard au boss.");
        }
    }

    public List<Detection> getAllDetections() {
        return detectionDAO.findAll();
    }
}
