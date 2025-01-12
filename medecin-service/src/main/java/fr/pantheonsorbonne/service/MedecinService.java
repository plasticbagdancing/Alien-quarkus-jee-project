package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.MedecinDAO;
import fr.pantheonsorbonne.dto.MedecinDTO;
import fr.pantheonsorbonne.entity.Medecin;
import fr.pantheonsorbonne.exception.MedecinAlreadyExistWithTheSameGalacticRegistrationNumberM;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MedecinService {
    @Inject
    MedecinDAO medecinDAO;
    public MedecinDTO getMedecinById(Long id) {
        Medecin medecin = medecinDAO.getById(id);
        if (medecin == null) {
            return null;
        }
        return new MedecinDTO(medecin.getId(), medecin.getNom(),medecin.getSpecialite(),medecin.getGalacticRegistrationNumberM()) ;

    }
    @Transactional
    public Long checkAndSaveMedecin(MedecinDTO medecinDTO) throws MedecinAlreadyExistWithTheSameGalacticRegistrationNumberM {
        if(medecinDAO.isMedecinPresent(medecinDTO.galacticRegistrationNumberM())){
            throw new MedecinAlreadyExistWithTheSameGalacticRegistrationNumberM();

        }
        Medecin medecin = new Medecin();
        medecin.setNom(medecinDTO.Nom());
        medecin.setGalacticRegistrationNumberM(medecinDTO.galacticRegistrationNumberM());
        medecin.setSpecialite(medecinDTO.Specialite());
        medecinDAO.saveMedecin(medecin);
        return medecin.getId();


    }
}
