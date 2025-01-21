package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.DetectionDAO;
import fr.pantheonsorbonne.entity.Detection;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class DetectionService {
    // Compteur statique pour l'idAlerte (incrémenté à chaque nouvelle détection)
    private static int idAlerteCounter = 1;

    @Inject
    DetectionDAO detectionDAO;

    @PostConstruct
    public void init() {
        if (detectionDAO == null) {
            System.err.println("DetectionDAO n'est pas injecté correctement !");
        } else {
            System.out.println("DetectionDAO est injecté avec succès.");
        }
    }

    public void handleDetection(Detection detection) {
        detectionDAO.save(detection);
//        // Logique métier pour gérer les alertes critiques
//        int seuilCritique = 100;
//        if (detection.getNbAliens() > seuilCritique || detection.getMessage().contains("Type_alien_dangereux")) {
//            System.out.println("CRITIQUE : Notifier le boss pour riposter.");
//        } else {
//            System.out.println("Pas critique : Envoyer une notification standard au boss.");
//        }
    }

//    public void enregistrer(String jsonMessage) {
//        // Créer une instance de Detection et remplir ses champs
//        Detection detection = new Detection();
//        detection.setDatetime(LocalDateTime.now()); // Date actuelle
//        detection.setMessage(jsonMessage); // Message reçu
//        detection.setNbAliens(0); // Valeur par défaut
//
//        // Sauvegarder la détection dans la base de données (log pour simulation)
//        System.out.println("Detection enregistrée : " + detection.getMessage());
//        handleDetection(detection);
//        // Vous pouvez ajouter ici le code pour persister `detection` dans la base de données
//    }



    public void enregistrer(String jsonMessage) {
        // Créer une instance de Detection
        Detection detection = new Detection();

        // Attribuer l'ID d'alerte unique (incrémentation)
        detection.setIdAlerte(idAlerteCounter++);

        // Date actuelle pour la détection
        detection.setDatetime(LocalDateTime.now());

        // Message reçu
        detection.setMessage(jsonMessage);

        // Extraire la Map depuis le message JSON (supposé sous forme de Map<String, Integer>)
        Map<String, Integer> stats = parseJsonToMap(jsonMessage);

        // Si la Map contient une clé "totalAliens", on l'affecte à nbAliens
        if (stats.containsKey("totalAliens")) {
            detection.setNbAliens(stats.get("totalAliens")); // Stocker totalAliens dans nbAliens
        } else {
            detection.setNbAliens(0); // Valeur par défaut si "totalAliens" n'est pas présent
        }

        // Sauvegarder la détection dans la base de données (log pour simulation)
        System.out.println("Detection enregistrée : " + detection.getMessage() + " avec nbAliens : " + detection.getNbAliens());
        handleDetection(detection);

        // Vous pouvez ajouter ici le code pour persister `detection` dans la base de données
    }

    // Méthode simulée pour convertir jsonMessage en Map<String, Integer>
    // En production, vous pourriez utiliser des outils comme ObjectMapper ou d'autres bibliothèques JSON
    private Map<String, Integer> parseJsonToMap(String jsonMessage) {
        Map<String, Integer> stats = new HashMap<>();

        // Exemple de parsing simulé : ici, le jsonMessage serait une chaîne de type "totalAliens=100,hostile=50,..."
        String[] pairs = jsonMessage.replace("{", "").replace("}", "").split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                Integer value = Integer.parseInt(keyValue[1].trim());
                stats.put(key, value);
            }
        }

        return stats;
    }
    public List<Detection> getAllDetections() {
        return detectionDAO.findAll();
    }

    public Detection getLastDetection() {
        // Récupère toutes les détections, triées par datetime décroissant
        return detectionDAO.findAll()
                .stream()
                .max(Comparator.comparing(Detection::getDatetime)) // Trouve la détection avec la date la plus récente
                .orElse(null); // Renvoie null si aucune détection n'est trouvée
    }

}
