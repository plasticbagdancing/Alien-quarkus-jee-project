package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//        from("sjms2:M1.bossService")
//                .unmarshal().json()
//                .log("nouvelle Alerte !!! : ${body}");

        from("sjms2:M1.bossService")
                .unmarshal().json() // Convertit le message JSON en un objet Java (par défaut, Map)
                .log("nouvelle Alerte !!! : ${body}")
                .split(simple("${body[message]}")) // Splite le champ "message"
                .log("Element du message splitté : ${body}") // Affiche chaque élément splitté
                .end();
    }

}
