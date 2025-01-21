package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.entity.Detection;
import fr.pantheonsorbonne.service.DetectionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class DetectionGateway {

    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    DetectionService detectionService;

    public void sendDetections() {
        try {
            // Récupérer la dernière détection
            Detection lastDetection = detectionService.getLastDetection();

            if (lastDetection == null) {
                System.out.println("Aucune détection disponible pour l'envoi.");
                return;
            }

            // Créer une version simplifiée de la détection sans le champ datetime
            Detection simplifiedDetection = new Detection();
            simplifiedDetection.setIdAlerte(lastDetection.getIdAlerte());
            simplifiedDetection.setNbAliens(lastDetection.getNbAliens());
            simplifiedDetection.setMessage(lastDetection.getMessage());

            //System.out.println(simplifiedDetection);

            // Envoyer le message via Camel
            producerTemplate.sendBody("direct:sendDetections", simplifiedDetection);

            //System.out.println("La détection a été envoyée avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi des détections : " + e.getMessage());
        }
    }


}
