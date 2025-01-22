package fr.pantheonsorbonne.camel;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import jakarta.inject.Inject;
import fr.pantheonsorbonne.service.DetectionService;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("sjms2:M1.BattlefieldStatsService")
                .log("Message reçu du champ de bataille : ${body}")
                .bean(DetectionService.class, "enregistrer")
                .log("Message traité et sauvegardé dans la base de données.");

        from("direct:sendDetections")
                .log("Envoi de la détection au boss : ${body}")
                .marshal().json()
                .convertBodyTo(String.class)
                .to("sjms2:M1.bossService")
                .log("Détection envoyée avec succès au service boss : ${body}");
    }

}
