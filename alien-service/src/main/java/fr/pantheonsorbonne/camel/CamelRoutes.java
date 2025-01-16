package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Route pour envoyer un Alien au champ de bataille
        from("direct:sendAlien")
                .log("Envoi d'un Alien au Champ-de-Bataille")
                .marshal().json()
                .to("sjms2:M1.BattlefieldService")  // Service Champ-de-Bataille
                .log("Alien envoyé avec succès : ${body}");


        // Route pour envoyer les statistiques au Champ-de-Bataille
        from("direct:sendAlienStats")
                .log("Envoi des statistiques au Champ-de-Bataille")
                .marshal().json()
                .to("sjms2:M1.BattlefieldStatsService")  // Service Champ-de-Bataille pour les statistiques
                .log("Statistiques envoyées avec succès : ${body}");

    }
}
