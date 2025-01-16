package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.ChampDeBataille;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ChampDeBatailleDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<ChampDeBataille> findAll() {
        return entityManager.createQuery("SELECT c FROM ChampDeBataille c", ChampDeBataille.class).getResultList();
    }

    public ChampDeBataille findById(Long id) {
        return entityManager.find(ChampDeBataille.class, id);
    }
    public void save(ChampDeBataille champDeBataille) {
        entityManager.persist(champDeBataille);
    }

    public void update(ChampDeBataille champDeBataille) {
        entityManager.merge(champDeBataille);
    }

    public void delete(Long id) {
        ChampDeBataille champDeBataille = findById(id);
        if (champDeBataille != null) {
            entityManager.remove(champDeBataille);
}
}
}