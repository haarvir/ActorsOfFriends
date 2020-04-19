package com.group.service;

import com.group.entity.Actor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class ActorService {
    @PersistenceContext(unitName = "actorPersistenceUnit")
    private EntityManager manager;

    public List<Actor> getAll() {
        return manager.createNamedQuery("findAllActors", Actor.class).getResultList();
    }


    public Actor findById(long id) {
        return manager.find(Actor.class, id);
    }

    @Transactional
    public void update(Actor actor) {
        manager.getTransaction().begin();
        manager.merge(actor);
        manager.getTransaction().commit();
    }

    @Transactional
    public void create(Actor actor) {
        manager.getTransaction().begin();
        manager.persist(actor);
        manager.getTransaction().commit();
    }

    @Transactional
    public void delete(Actor actor) {
        manager.getTransaction().begin();
        if (!manager.contains(actor)) {
            actor = manager.merge(actor);
        }

        manager.remove(actor);
        manager.getTransaction().commit();
    }
}
