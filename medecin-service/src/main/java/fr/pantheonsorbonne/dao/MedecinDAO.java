package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Medecin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class MedecinDAO {
    @Inject
    EntityManager grnm;
    public boolean isMedecinPresent(String medecinGalacticRegistrationNumberM){
        return !grnm.createQuery("SELECT m from Medecin m where m.galacticRegistrationNumberM=:galacticRegistrationNumberM")
                .setParameter("galacticRegistrationNumberM", medecinGalacticRegistrationNumberM)
                .getResultList().isEmpty();

    }

    public void saveMedecin(Medecin medecin) {
        grnm.persist(medecin);

    }

    public Medecin getById(Long id) {
        return grnm.find(Medecin.class, id);
    }
}
