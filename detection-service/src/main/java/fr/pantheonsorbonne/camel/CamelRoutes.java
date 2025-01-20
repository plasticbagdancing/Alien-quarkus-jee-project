package fr.pantheonsorbonne.camel;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import jakarta.inject.Inject;
import fr.pantheonsorbonne.service.DetectionService;


public class CamelRoutes extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("sjms2:M1.BattlefieldStatsService") // Route venant de BattlefieldService
                .log("Message reçu du champ de bataille : ${body}") // Log pour afficher ce qu'on reçoit
                .bean(DetectionService.class, "enregistrer") // Appel de la méthode handleDetection dans DetectionService
                .log("Message traité et sauvegardé dans la base de données.");
    }

}
