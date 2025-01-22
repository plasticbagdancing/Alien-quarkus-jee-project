package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ChampDeBatailleDAO;
import fr.pantheonsorbonne.dto.AlienDTO;
import fr.pantheonsorbonne.dto.ChampDeBatailleDTO;
import fr.pantheonsorbonne.dto.SoldatDTO;
import fr.pantheonsorbonne.entity.ChampDeBataille;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@ApplicationScoped
public class ChampDeBatailleService {

    @Inject
    ChampDeBatailleDAO champDeBatailleDAO;

    public List<ChampDeBatailleDTO> getAll() {
        return champDeBatailleDAO.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ChampDeBatailleDTO getById(Long id) {
        ChampDeBataille champDeBataille = champDeBatailleDAO.findById(id);
        if (champDeBataille == null) {
            throw new RuntimeException("Champ de bataille non trouvé !");
        }
        return toDTO(champDeBataille);
    }
    @Transactional
    public void create(ChampDeBatailleDTO dto) {
        ChampDeBataille champDeBataille = toEntity(dto);
        champDeBatailleDAO.save(champDeBataille);
    }

    @Transactional
    public void update(Long id, ChampDeBatailleDTO dto) {
        ChampDeBataille existing = champDeBatailleDAO.findById(id);
        if (existing == null) {
            throw new RuntimeException("Champ de bataille non trouvé !");
        }
        existing.setEtat(dto.etat());
        existing.setNombreMorts(dto.nombreMorts());
        existing.setNombreBlesses(dto.nombreBlesses());
        champDeBatailleDAO.update(existing);
    }

    @Transactional
    public void delete(Long id) {
        champDeBatailleDAO.delete(id);
    }
    private ChampDeBatailleDTO toDTO(ChampDeBataille champDeBataille) {
        return new ChampDeBatailleDTO(
                champDeBataille.getEtat(),
                champDeBataille.getNombreMorts(),
                champDeBataille.getNombreBlesses()
        );
    }
    private ChampDeBataille toEntity(ChampDeBatailleDTO dto) {
        ChampDeBataille champDeBataille = new ChampDeBataille();
        champDeBataille.setEtat(dto.etat());
        champDeBataille.setNombreMorts(dto.nombreMorts());
        champDeBataille.setNombreBlesses(dto.nombreBlesses());
        return champDeBataille;
}


    public ChampDeBatailleDTO combattre(List<SoldatDTO> soldats, List<AlienDTO> aliens) {
        int mortsSoldats = 0;
        int blessesSoldats = 0;
        int mortsAliens = 0;
        int blessesAliens = 0;


        for (SoldatDTO soldat : soldats) {
            for (AlienDTO alien : aliens) {
                boolean combatGagne = false;

                //Combats entre experts et opportunistes
                if ("Expert".equals(soldat.categorie()) && "Opportuniste".equals(alien.type())) {
                    combatGagne = true; // Les experts gagnent
                }
                //Combats entre intermédiaires et hostiles
                else if ("Intermédiaire".equals(soldat.categorie()) && "Hostile".equals(alien.type())) {
                    combatGagne = true; // Les intermédiaires gagnent
                }
                //Combats entre débutants et alliés
                else if ("Débutant".equals(soldat.categorie()) && "Allied".equals(alien.type())) {
                    combatGagne = true; // Les débutants gagnent
                }

                // maj des victimes et les blessés selon le combat
                if (combatGagne) {
                    mortsAliens++;  // Alien tué
                    blessesSoldats++; // Le soldat est blessé
                } else {
                    mortsSoldats++;  // Soldat tué
                    blessesAliens++; // L'alien est blessé
                }
            }
        }


        String etat = (mortsAliens > mortsSoldats) ? "Perdu" : "Gagné";

        // Retourner l'état du combat sous forme de DTO
        return new ChampDeBatailleDTO(etat, mortsSoldats + blessesSoldats, mortsAliens + blessesAliens);
    }


}