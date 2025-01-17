package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.AlienDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class AlienGateway {

    @Inject
    ProducerTemplate producerTemplate;

    private static int totalAliens = 0;
    private static int hostileAliens = 0;
    private static int opportunistAliens = 0;
    private static int alliedAliens = 0;

    // Méthode pour générer plusieurs aliens avec un minimum de 100 par type
    public void sendRandomAlien() {
        String[] types = {"hostile", "opportunist", "allied"};
        String alienType = types[(int) (Math.random() * types.length)];  // Choisir un type d'alien aléatoire

        AlienDTO alien = new AlienDTO(
                (long) (Math.random() * 1000),  // ID aléatoire
                alienType,  // Type choisi aléatoirement
                "GRN-" + (int) (Math.random() * 1000)  // Numéro d'enregistrement aléatoire
        );

        producerTemplate.sendBody("direct:sendAlien", getAlienStats());

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

    // Méthode pour récupérer les statistiques des aliens générés
    public static String getAlienStats() {
        return String.format("{\"totalAliens\": %d, \"hostile\": %d, \"opportunist\": %d, \"allied\": %d}",
                totalAliens, hostileAliens, opportunistAliens, alliedAliens);
    }
}
