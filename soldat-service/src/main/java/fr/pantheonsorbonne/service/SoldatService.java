package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.SoldatDAO;
import fr.pantheonsorbonne.dto.SoldatDTO;
import fr.pantheonsorbonne.entity.Soldat;
import fr.pantheonsorbonne.exception.InvalidSoldatException;
import fr.pantheonsorbonne.exception.SoldatAlredayExistWithTheSameGalacticRegistrationNumberS;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Transactional
    public List<SoldatDTO> genererSoldats(Map<String, Integer> stats) {
        List<SoldatDTO> soldats = new ArrayList<>();

        for (int i = 0; i < stats.getOrDefault("experts", 0); i++) {
            SoldatDTO soldat = creerSoldat("Expert");
            enregistrerSoldatDansBase(soldat);  // Enregistrer le soldat en base de données
            soldats.add(soldat);
        }

        for (int i = 0; i < stats.getOrDefault("intermediates", 0); i++) {
            SoldatDTO soldat = creerSoldat("Intermédiaire");
            enregistrerSoldatDansBase(soldat);  // Enregistrer le soldat en base de données
            soldats.add(soldat);
        }

        for (int i = 0; i < stats.getOrDefault("beginners", 0); i++) {
            SoldatDTO soldat = creerSoldat("Débutant");
            enregistrerSoldatDansBase(soldat);  // Enregistrer le soldat en base de données
            soldats.add(soldat);
        }

        return soldats;
    }

    private SoldatDTO creerSoldat(String categorie) {
        return new SoldatDTO(
                null,
                "Soldat_" + UUID.randomUUID().toString().substring(0, 8),
                categorie,
                "En forme",
                UUID.randomUUID().toString()
        );
    }

    @Transactional
    public void enregistrerSoldatDansBase(SoldatDTO soldatDTO) {

        Soldat soldat = new Soldat();
        soldat.setNom(soldatDTO.Nom());
        soldat.setCategorie(soldatDTO.categorie());
        soldat.setEtatdesante(soldatDTO.etatdesante());
        soldat.setGalacticRegistrationNumberS(soldatDTO.galacticRegistrationNumberS());

         soldatDAO.saveSoldat(soldat);
    }

    public List<SoldatDTO> getSoldats() {
        List<Soldat> soldats = soldatDAO.findAll();
        return convertToDTO(soldats);
    }

    private List<SoldatDTO> convertToDTO(List<Soldat> soldats) {
        return soldats.stream()
                .map(soldat -> new SoldatDTO(
                        soldat.getId(),
                        soldat.getNom(),
                        soldat.getCategorie(),
                        soldat.getEtatdesante(),
                        soldat.getGalacticRegistrationNumberS()))
                .collect(Collectors.toList());
    }

}
