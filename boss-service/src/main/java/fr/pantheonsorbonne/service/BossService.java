package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.BossDAO;
import fr.pantheonsorbonne.dto.BossDTO;
import fr.pantheonsorbonne.entity.Boss;
import fr.pantheonsorbonne.exception.BossAlreadyExistWithTheSamegalacticRegistrationNumberBoss;
import fr.pantheonsorbonne.exception.InvalidBossException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BossService {
    @Inject
    BossDAO bossDAO;

    public BossDTO getBossById(Long id) {
        Boss boss = bossDAO.getById(id);
        if(boss == null){
            return null;
        }
        return new BossDTO(boss.getId(), boss.getNom() ,boss.getGalacticRegistrationNumberBoss());
    }
    @Transactional
    public Long checkAndSaveBoss(BossDTO bossDTO) throws InvalidBossException, BossAlreadyExistWithTheSamegalacticRegistrationNumberBoss {
        if (!bossDTO.galacticRegistrationNumberBoss().contains("-")){
            throw new InvalidBossException("galacticRegistrationNumberBoss is wrong");
        }
        if(bossDAO.isBossPresent(bossDTO.galacticRegistrationNumberBoss())){
            throw new BossAlreadyExistWithTheSamegalacticRegistrationNumberBoss();
        }
        Boss boss = new Boss();
        boss.setGalacticRegistrationNumberBoss(bossDTO.galacticRegistrationNumberBoss());
        boss.setNom(bossDTO.Nom());
        bossDAO.saveBoss(boss);
        return boss.getId();
    }
}
