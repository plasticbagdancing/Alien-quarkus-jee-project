package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.DetectionDAO;
import fr.pantheonsorbonne.entity.Detection;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class DetectionService {

    @Inject
    private DetectionDAO detectionDAO;

    public void handleDetection(Detection detection) {
        detectionDAO.save(detection);

        // Logique métier
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
