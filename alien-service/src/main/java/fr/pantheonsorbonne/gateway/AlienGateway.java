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



    public void sendRandomAlien() {

        int numberOfAliensToGenerate = 100;
        String[] types = {"hostile", "opportunist", "allied"};
        producerTemplate.sendBody("direct:sendAlien", getAlienStats());

        for (int i = 0; i < numberOfAliensToGenerate; i++) {
            String alienType = types[(int) (Math.random() * types.length)];

            AlienDTO alien = new AlienDTO(
                    (long) (Math.random() * 1000),
                    alienType,
                    "GRN-" + (int) (Math.random() * 1000)
            );
            producerTemplate.sendBody("direct:sendAlien", alien);


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

        sendAlienStatsToBattlefield();
    }
    public void sendAlienStatsToBattlefield() {

        Map<String, Integer> stats = new HashMap<>();
        stats.put("totalAliens", totalAliens);
        stats.put("hostile", hostileAliens);
        stats.put("opportunist", opportunistAliens);
        stats.put("allied", alliedAliens);

        producerTemplate.sendBody("direct:sendAlienStats", stats);
    }

    // Méthode pour récupérer les statistiques des aliens générés
    public String getAlienStats() {
        return String.format("{\"totalAliens\": %d, \"hostile\": %d, \"opportunist\": %d, \"allied\": %d}",
                totalAliens, hostileAliens, opportunistAliens, alliedAliens);
    }
}

