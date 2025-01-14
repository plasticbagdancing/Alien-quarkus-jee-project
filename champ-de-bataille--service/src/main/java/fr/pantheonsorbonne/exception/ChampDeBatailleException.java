
package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.exception.ChampDeBatailleException;
import fr.pantheonsorbonne.dto.ChampDeBatailleDTO;

public class ChampDeBatailleService extends RuntimeException {

    public void mettreAJourEtatChampDeBataille(ChampDeBatailleDTO champDeBatailleDTO) {
        if (champDeBatailleDTO.nombreMorts() < 0 || champDeBatailleDTO.nombreBlesses() < 0) {
            throw new ChampDeBatailleException("Le nombre de morts ou de blessés ne peut pas être négatif");
        }


    }
}
