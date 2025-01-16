package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ChampDeBatailleDAO;
import fr.pantheonsorbonne.dto.ChampDeBatailleDTO;
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
}