package fr.pantheonsorbonne.dto;

import java.time.LocalDateTime;


public record DetectionDTO(
        int idAlerte,
        LocalDateTime datetime,
        int nbAliens,
        String message
) {
}
