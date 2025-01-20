package fr.pantheonsorbonne.gateway;


import fr.pantheonsorbonne.dto.AlienDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class AlienGateway {


    @Inject
    ProducerTemplate producerTemplate;


    private int totalAliens = 0;
    private int hostileAliens = 0;
    private int opportunistAliens = 0;
    private int alliedAliens = 0;


    // Méthode pour générer plusieurs aliens avec un minimum de 100 par type
    public void sendRandomAlien() {
        // Nombre d'aliens à générer par appel
        int numberOfAliensToGenerate = 100;  // Modifier ce nombre selon votre besoin (150, etc.

        String[] types = {"hostile", "opportunist", "allied"};

        producerTemplate.sendBody("direct:sendAlien", getAlienStats());

        for (int i = 0; i < numberOfAliensToGenerate; i++) {
            String alienType = types[(int) (Math.random() * types.length)];  // Choisir un type d'alien aléatoire

            AlienDTO alien = new AlienDTO(
                    (long) (Math.random() * 1000),  // ID aléatoire
                    alienType,  // Type choisi aléatoirement
                    "GRN-" + (int) (Math.random() * 1000)  // Numéro d'enregistrement aléatoire
            );


            producerTemplate.sendBody("direct:sendAlien", alien); // Envoi de l'alien généré au champ de bataille


            // Mise à jour des compteurs
            totalAliens++;
            switch (alienType) {
                case "hostile":
                    hostileAliens++;
                    break;
                case "opportunist":
                    opportunistAliens++;
                    break;
                case "allied":
                    alliedAliens++;
                    break;
            }
        }


        // Après génération des aliens, envoyer les statistiques au service de détection
        sendAlienStatsToBattlefield();
    }


    public void sendAlienStatsToBattlefield() {
        // Création d'un Map avec les statistiques
        Map<String, Integer> stats = new HashMap<>();
        stats.put("totalAliens", totalAliens);
        stats.put("hostile", hostileAliens);
        stats.put("opportunist", opportunistAliens);
        stats.put("allied", alliedAliens);

        // Envoi des statistiques via Camel, en envoyant directement le Map
        producerTemplate.sendBody("direct:sendAlienStats", stats);
    }

    // Méthode pour récupérer les statistiques des aliens générés
    public String getAlienStats() {
        return String.format("{\"totalAliens\": %d, \"hostile\": %d, \"opportunist\": %d, \"allied\": %d}",
                totalAliens, hostileAliens, opportunistAliens, alliedAliens);
    }
}

