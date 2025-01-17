package fr.pantheonsorbonne.gateway;


import fr.pantheonsorbonne.dto.AlienDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;


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
        int numberOfAliensToGenerate = 100;  // Modifier ce nombre selon votre besoin (150, etc.)


        String[] types = {"hostile", "opportunist", "allied"};


<<<<<<< HEAD
        producerTemplate.sendBody("direct:sendAlien", getAlienStats());
=======
        for (int i = 0; i < numberOfAliensToGenerate; i++) {
            String alienType = types[(int) (Math.random() * types.length)];  // Choisir un type d'alien aléatoire
>>>>>>> b944f671ba76cc20ba9e071eda0b18899a79cb3e


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


    private void sendAlienStatsToBattlefield() {
        // Création d'un JSON avec les statistiques
        String statsJson = String.format("{\"totalAliens\": %d, \"hostile\": %d, \"opportunist\": %d, \"allied\": %d}",
                totalAliens, hostileAliens, opportunistAliens, alliedAliens);


        // Envoi des statistiques via Camel
        producerTemplate.sendBody("direct:sendAlienStats", statsJson);
    }


    // Méthode pour récupérer les statistiques des aliens générés
    public String getAlienStats() {
        return String.format("{\"totalAliens\": %d, \"hostile\": %d, \"opportunist\": %d, \"allied\": %d}",
                totalAliens, hostileAliens, opportunistAliens, alliedAliens);
    }
}

