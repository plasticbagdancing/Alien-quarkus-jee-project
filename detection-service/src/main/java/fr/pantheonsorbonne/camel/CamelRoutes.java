package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.entity.Detection;
import fr.pantheonsorbonne.service.DetectionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.CamelContext;

import java.time.LocalDateTime;

@ApplicationScoped
public class CamelConfig extends RouteBuilder {

    @Inject
    DetectionService detectionService;

    @Override
    public void configure() throws Exception {
        from("sjms2:M1.BattlefieldService")
                .log("Message reçu du champ de bataille : ${body}")
                .process(exchange -> {
                    String jsonMessage = exchange.getIn().getBody(String.class);
                    Detection detection = new Detection();
                    detection.setDatetime(LocalDateTime.now());
                    detection.setMessage(jsonMessage);
                    detection.setNbAliens(0);
                    detection.setIdRadar(1);
                    detection.setIdAlerte(1);

                    detectionService.handleDetection(detection);

                    log.info("Detection enregistrée : " + detection.getMessage());
                })
                .log("Message traité et sauvegardé dans la base de données.");
    }
}
