package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Route du Champ de Bataille vers le Boss
        from("direct:champDeBatailleResult")
                .routeId("champDeBatailleToBossRoute")
                .log("Envoi du message au Boss: ${body}")
                .to("direct:bossNotification");
    }

}


