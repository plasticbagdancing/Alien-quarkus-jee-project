package fr.pantheonsorbonne.camel;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import jakarta.inject.Inject;
import fr.pantheonsorbonne.entity.Detection;
import fr.pantheonsorbonne.service.DetectionService;
import java.time.LocalDateTime;


public class CamelRoutes extends RouteBuilder {

    @Inject
    DetectionService detectionService; // Injection du service directement ici

    @Override
    public void configure() throws Exception {
        from("sjms2:M1.BattlefieldService") // Route venant de BattlefieldService
                .log("Message reçu du champ de bataille : ${body}") // Log pour afficher ce qu'on reçoit
                .process(exchange -> {
                    // Récupérer le message JSON tel quel
                    String jsonMessage = exchange.getIn().getBody(String.class);
                    System.out.println("Message JSON reçu : " + jsonMessage);

                    // Créer une instance de Detection et remplir ses champs
                    Detection detection = new Detection();
                    detection.setDatetime(LocalDateTime.now()); // Date actuelle
                    detection.setMessage(jsonMessage); // Message reçu
                    detection.setNbAliens(0); // Valeur par défaut
                    detection.setIdRadar(1); // ID arbitraire
                    detection.setIdAlerte(1); // ID arbitraire

                    // Sauvegarder la détection dans la base de données
                    detectionService.handleDetection(detection);
                    System.out.println("Detection enregistrée : " + detection.getMessage());
                })
                .log("Message traité et sauvegardé dans la base de données.");
    }
}
