package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
import fr.pantheonsorbonne.dto.AlienDTO;

public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Route pour envoyer un Alien au champ de bataille
        from("direct:sendAlien")
                .log("Envoi d'un Alien au champ de bataille")
                .marshal().json()
                .to("sjms2:M1.BattlefieldService")
                .log("Alien envoyé avec succès : ${body}");
    }
}
