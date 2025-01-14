package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ChampDeBatailleDAO;
import fr.pantheonsorbonne.dto.ChampDeBatailleDTO;
import fr.pantheonsorbonne.entity.ChampDeBataille;
import fr.pantheonsorbonne.exception.ChampDeBatailleException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ChampDeBatailleService {

    @Inject
    ChampDeBatailleDAO champDeBatailleDAO;

    public ChampDeBatailleDTO getEtatChampDeBataille() {

        ChampDeBataille champDeBataille = champDeBatailleDAO.getEtatChampDeBataille();

        if(champDeBataille == null){
            throw new ChampDeBatailleNotFoundException("Champ de bataille non trouvé");
        }

        ChampDeBatailleDTO champDeBatailleDTO = new ChampDeBatailleDTO();
        champDeBatailleDTO.setEtat(champDeBataille.getEtat());
        champDeBatailleDTO.setNombreMorts(champDeBataille.getNombreMorts());
        champDeBatailleDTO.setNombreBlesses(champDeBataille.getNombreBlesses());

        return champDeBatailleDTO;
    }

    @Transactional
    public void mettreAJourEtatChampDeBataille(ChampDeBatailleDTO champDeBatailleDTO) {
        ChampDeBataille champDeBataille = champDeBatailleDAO.getEtatChampDeBataille();

        if (champDeBataille == null) {
            throw new ChampDeBatailleNotFoundException("Champ de bataille non trouvé");
        }

        champDeBataille.setEtat(champDeBatailleDTO.getEtat());
        champDeBataille.setNombreMorts(champDeBatailleDTO.getNombreMorts());
        champDeBataille.setNombreBlesses(champDeBatailleDTO.getNombreBlesses());

        champDeBatailleDAO.updateChampDeBataille(champDeBataille);
    }

    public void calculerStatistiquesBataille() {
        int morts = (int) (Math.random() * 100); //  calcul aléatoire
        int blesses = (int) (Math.random() * 50); // calcul aléatoire

        // Maj de l'etat
        ChampDeBataille champDeBataille = champDeBatailleDAO.getEtatChampDeBataille();
        champDeBataille.setNombreMorts(morts);
        champDeBataille.setNombreBlesses(blesses);

        champDeBatailleDAO.updateChampDeBataille(champDeBataille);
    }
}
