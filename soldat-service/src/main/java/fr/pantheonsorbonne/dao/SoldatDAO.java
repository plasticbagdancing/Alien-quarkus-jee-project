package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Soldat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class SoldatDAO {
    @Inject
    EntityManager grns;
    public boolean isSoldatPresent(String soldatGalacticRegistrationNumberS){
        return !grns.createQuery("SELECT s from Soldat s where s.galacticRegistrationNumberS =:galacticRegistrationNumberS")
                .setParameter("galacticRegistrationNumberS",soldatGalacticRegistrationNumberS)
                .getResultList().isEmpty();
    }

    public void saveSoldat(Soldat soldat) {
        grns.persist(soldat);

    }

    public Soldat getById(Long id) {

        return grns.find(Soldat.class, id);
    }
}
