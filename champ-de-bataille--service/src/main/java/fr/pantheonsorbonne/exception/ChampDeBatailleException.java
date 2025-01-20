
package fr.pantheonsorbonne.exception;
import fr.pantheonsorbonne.dto.ChampDeBatailleDTO;

public class ChampDeBatailleException extends RuntimeException {

    public ChampDeBatailleException(String s) {

    }

    public void mettreAJourEtatChampDeBataille(ChampDeBatailleDTO champDeBatailleDTO) {
        if (champDeBatailleDTO.nombreMorts() < 0 || champDeBatailleDTO.nombreBlesses() < 0) {
            throw new ChampDeBatailleException("Le nombre de morts ou de blessés ne peut pas être négatif");
        }


    }
}
