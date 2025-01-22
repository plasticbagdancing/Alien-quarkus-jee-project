package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Boss;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
@ApplicationScoped
public class BossDAO {
    @Inject
    EntityManager grnb ;
    public boolean isBossPresent(String bossGalacticRegistrationNumberBoss){
        return !grnb.createQuery("SELECT b from Boss b where b.galacticRegistrationNumberBoss=:galacticRegistrationNumberBoss ")
                .setParameter("galacticRegistrationNumberBoss", bossGalacticRegistrationNumberBoss)
                .getResultList().isEmpty();
    }
    public void saveBoss(Boss boss) {
        grnb.persist(boss);

    }
    public Boss getById(Long id) {
        return grnb.find(Boss.class, id);
    }
}
