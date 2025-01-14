package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Arsenal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ArsenalDAO {

    @Inject
    EntityManager entityManager;

    public boolean isArsenalPresent(String typeArmes) {
        return !entityManager.createQuery("SELECT a FROM Arsenal a WHERE a.type = :typeArmes")
                .setParameter("typeArmes", typeArmes)
                .getResultList().isEmpty();
    }


    public Arsenal getById(Long id) {
        return entityManager.find(Arsenal.class, id);
    }
}
