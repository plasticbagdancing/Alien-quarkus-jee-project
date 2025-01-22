package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:sendAlien")
                .log("Envoi d'un Alien au Champ-de-Bataille")
                .marshal().json()
                .to("sjms2:M1.BattlefieldService")
                .log("Alien envoyé avec succès : ${body}");



        from("direct:sendAlienStats")
                .log("Envoi des statistiques au Champ-de-Bataille")

                .to("sjms2:M1.BattlefieldStatsService")
                .log("Statistiques envoyées avec succès : ${body}");

    }
}
