package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.gateway.BossGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Inject
    BossGateway bossGateway;

    @Override
    public void configure() throws Exception {
        from("sjms2:M1.bossService")
                .unmarshal().json()
                .log("Nouvelle Alerte !!! : ${body}")

                .setBody(simple("${body[message]}"))
                .bean(bossGateway, "toSoldierAssignment")
                .log("Soldier Assignment: ${body}");


        from("direct:notifySoldiers")
                .marshal().json()
                .log("${body}")
                .to("sjms2:M1.soldatservice");
    }

}
