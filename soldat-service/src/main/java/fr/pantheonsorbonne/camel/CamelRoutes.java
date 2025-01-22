package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.service.SoldierAggregatorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    SoldierAggregatorService soldierAggregatorService;

    @Override
    public void configure() throws Exception {


        from("sjms2:M1.soldatservice")
                .unmarshal().json()
                .log("Message reçu : ${body}")
                .bean(soldierAggregatorService, "accumulate")
                .log("Accumulation terminée avec succès.");



        from("direct:sendSoldatStats")
                .log("Envoi des soldats au champ de bataille: ${body}")
                .marshal().json()
                .to("sjms2:M1.SoldatStatsService")
                .log("Soldats envoyés au champ de bataille avec succès.");

    }
}
