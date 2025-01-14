package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ArsenalDAO;
import fr.pantheonsorbonne.dto.ArsenalDTO;
import fr.pantheonsorbonne.entity.Arsenal;
import fr.pantheonsorbonne.exception.ArsenalAlreadyExistsException;
import fr.pantheonsorbonne.exception.InvalidArsenalException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ArsenalService {

    @Inject
    ArsenalDAO arsenalDAO;

    public ArsenalDTO getArsenalById(Long id) {
        Arsenal arsenal = arsenalDAO.getById(id);
        if (arsenal == null) {
            return null;
        }
        return new ArsenalDTO(arsenal.getId(), arsenal.getType(), arsenal.getDuration(), arsenal.getQuantity());
    }

    @Transactional
    public Long checkAndSaveArsenal(ArsenalDTO arsenalDTO) throws InvalidArsenalException, ArsenalAlreadyExistsException {
        if (arsenalDTO.Type() == null || arsenalDTO.Type().isBlank()) {
            throw new InvalidArsenalException("Le type d'arme est invalide.");
        }

        if (arsenalDAO.isArsenalPresent(arsenalDTO.Type())) {
            throw new ArsenalAlreadyExistsException();
        }

        Arsenal arsenal = new Arsenal();
        arsenal.setType(arsenalDTO.Type());
        arsenal.setDuration(arsenalDTO.Duration());
        arsenal.setQuantity(arsenalDTO.Quantity());

        return arsenal.getId();
    }
}
