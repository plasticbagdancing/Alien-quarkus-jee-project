package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Arsenal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ArsenalDAO {

    @Inject
    EntityManager entityManager;

    private final List<Arsenal> database = new ArrayList<>();

    //    public boolean isArsenalPresent(String typeArmes) {
//        return !entityManager.createQuery("SELECT a FROM Arsenal a WHERE a.Type = :typeArmes")
//                .setParameter("typeArmes", typeArmes)
//                .getResultList().isEmpty();
//    }
//
//
//    public Arsenal getById(Long id) {
//        System.out.println("L'arsenal est" + entityManager.find(Arsenal.class, id));
//        return entityManager.find(Arsenal.class, id);
//    }
//
//    public void saveArsenal(Arsenal arsenal) {
//
//        database.add(arsenal);
//        System.out.println("Arsenal saved: " + arsenal.getId());
//    }
    @Transactional
    public Arsenal getById(Long id) {
        System.out.println("L'arsenal est" + entityManager.find(Arsenal.class, id));
        return entityManager.find(Arsenal.class, id);
    }

    @Transactional
    public void saveArsenal(Arsenal arsenal) {
        database.add(arsenal);
//        entityManager.persist(arsenal);
//        entityManager.flush();
        System.out.println("Arsenal saved: " + arsenal.getId());
    }
    public List<Arsenal> getAll() {
        return database;
    }
}
