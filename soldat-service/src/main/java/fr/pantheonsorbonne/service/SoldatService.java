package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.SoldatDAO;
import fr.pantheonsorbonne.dto.SoldatDTO;
import fr.pantheonsorbonne.entity.Soldat;
import fr.pantheonsorbonne.exception.InvalidSoldatException;
import fr.pantheonsorbonne.exception.SoldatAlredayExistWithTheSameGalacticRegistrationNumberS;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SoldatService {
    @Inject
    SoldatDAO soldatDAO;

    public SoldatDTO getSoldatById(Long id) {
        Soldat soldat = soldatDAO.getById(id);
        if(soldat == null) {
            return null;
        }
        return new SoldatDTO(soldat.getId(),soldat.getNom(),soldat.getCategorie(),soldat.getEtatdesante(), soldat.getGalacticRegistrationNumberS());
    }
    @Transactional

    public Long checkAndSaveSoldat(SoldatDTO soldatDTO) throws InvalidSoldatException, SoldatAlredayExistWithTheSameGalacticRegistrationNumberS {
        if (!soldatDTO.categorie().matches("[dDeEiI]")) {
            throw new InvalidSoldatException("categorie is invalid");
        }
        if (soldatDAO.isSoldatPresent(soldatDTO.galacticRegistrationNumberS())) {
            throw new SoldatAlredayExistWithTheSameGalacticRegistrationNumberS();
        }
        Soldat soldat = new Soldat();
        soldat.setCategorie(soldatDTO.categorie());
        soldat.setGalacticRegistrationNumberS(soldatDTO.galacticRegistrationNumberS());
        soldat.setEtatdesante(soldatDTO.etatdesante());
        soldat.setNom(soldatDTO.Nom());
        soldatDAO.saveSoldat(soldat);
        return soldat.getId();






    }
}
