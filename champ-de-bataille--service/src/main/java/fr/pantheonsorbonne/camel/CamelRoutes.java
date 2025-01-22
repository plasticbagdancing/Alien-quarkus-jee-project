package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.service.ChampDeBatailleService;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {

    @Inject
    ChampDeBatailleService champDeBatailleService;

    @Override
    public void configure() throws Exception {

//
//        from("sjms2:M1.BattlefieldService") // Route venant de BattlefieldService
//                .log("Les aliens arrivent  : ${body}"); // Log du message brut


//        // Route pour envoyer les soldats au service de bataille via JMS
//        from("sjms2:M1.SoldatStatsService")
//                .log("Les soldats sont arrivés au champ de bataille : ${body}")
//                .to("direct:combattre");
//
//        // Route pour recevoir les aliens et soldats et commencer le combat
//        from("sjms2:M1.BattlefieldService")
//                .log("Les aliens arrivent : ${body}")
//                .to("direct:combattre");
//
//        // Route pour commencer le combat
//        from("direct:combattre")
//                .bean(champDeBatailleService, "combattre")  // Appel de la méthode combattre dans ChampDeBatailleService
//                .log("Combat terminé : ${body}");  // Envoi du résultat du combat à un autre service via JMS
//    }
        // Route pour recevoir les aliens via JMS
        from("sjms2:M1.BattlefieldService")  // La route JMS qui reçoit les aliens
                .log("Les aliens arrivent : ${body}") ; // Affiche le corps du message (les aliens)
//                .to("direct:combattre");  // Passe au traitement du combat

        // Route pour recevoir les soldats via JMS
        from("sjms2:M1.SoldatStatsService")  // La route JMS qui reçoit les soldats
                .log("Les soldats sont arrivés : ${body}") ;
//                .to("direct:combattre");




    }

}



