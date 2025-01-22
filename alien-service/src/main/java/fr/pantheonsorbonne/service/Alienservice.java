package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.AlienDAO;
import fr.pantheonsorbonne.dto.AlienDTO;
import fr.pantheonsorbonne.entity.Alien;
import fr.pantheonsorbonne.exception.AlienAlreadyExistWithTheSameGalacticRegistrationNumber;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
@ApplicationScoped
public class Alienservice {
    @Inject
    AlienDAO alienDAO;
    public AlienDTO getAlienByID(Long id) {
        Alien alien = alienDAO.getById(id);
        if(alien == null){
            return null;
        }
        return new AlienDTO(alien.getId(),alien.getType(),alien.getGalacticRegistrationNumber());
    }
    @Transactional
    public Long checkandAlien(AlienDTO alienDTO) throws AlienAlreadyExistWithTheSameGalacticRegistrationNumber {
        // vérification existence
        if (alienDAO.isAlienPresent(alienDTO.galacticRegistrationNumber())){
            throw new AlienAlreadyExistWithTheSameGalacticRegistrationNumber();

        }
        Alien alien = new Alien();
        alien.setGalacticRegistrationNumber(alienDTO.galacticRegistrationNumber());
        alien.setType(alienDTO.type());
        alienDAO.saveAlien(alien);
        return alien.getId();

    }
}
