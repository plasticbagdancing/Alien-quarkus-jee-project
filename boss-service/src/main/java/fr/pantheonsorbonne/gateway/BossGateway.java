package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.SoldierAssignment;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class BossGateway {

    @Inject
    ProducerTemplate producerTemplate;


    public void toSoldierAssignment(String message) {
        int experts = 0, intermediates = 0, beginners = 0;


        message = message.replace("{", "").replace("}", "");


        for (String pair : message.split(", ")) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0].trim();
            int value = Integer.parseInt(keyValue[1].trim()); // Convertir en entier


            switch (key) {
                case "opportunist" -> experts += value;
                case "hostile" -> intermediates += value;
                case "allied" -> beginners += value;

            }
        }
        SoldierAssignment sa = new SoldierAssignment(experts, intermediates, beginners);


        if (producerTemplate != null) {
            producerTemplate.sendBody("direct:notifySoldiers", sa);
        } else {
            System.err.println("Erreur : ProducerTemplate est null, impossible d'envoyer le message.");
        }
    }
}

