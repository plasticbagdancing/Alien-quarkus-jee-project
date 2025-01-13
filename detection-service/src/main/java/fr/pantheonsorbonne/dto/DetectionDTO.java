package fr.pantheonsorbonne.dto;

import java.time.LocalDateTime;

public record DetectionDTO(
        int idAlerte,
        LocalDateTime datetime, // Le champ "datetime" doit être défini ici
        int idRadar,
        int nbAliens,
        String message
) {
}
