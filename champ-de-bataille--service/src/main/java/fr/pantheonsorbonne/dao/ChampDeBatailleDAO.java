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
<<<<<<< HEAD
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
=======
>>>>>>> b944f671ba76cc20ba9e071eda0b18899a79cb3e
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