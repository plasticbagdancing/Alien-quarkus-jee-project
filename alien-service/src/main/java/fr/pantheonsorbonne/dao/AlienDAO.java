package fr.pantheonsorbonne.dao;
import fr.pantheonsorbonne.entity.Alien;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class AlienDAO {
    @Inject
    EntityManager grn;
    public boolean isAlienPresent(String galacticRegistrationNumber){
        return !grn.createQuery("select a from Alien a where a.galacticRegistrationNumber = :galacticRegistrationNumber")
                .setParameter("galacticRegistrationNumber", galacticRegistrationNumber)
                .getResultList().isEmpty();
    }

    public void saveAlien(Alien alien) {
        grn.persist(alien);
    }

    public Alien getById(Long id) {
        return grn.find(Alien.class, id);

    }
}
