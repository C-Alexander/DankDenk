package com.dank.contexts;

import com.dank.entities.Meme;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;


public class JPAMemeContext implements MemeContext {

    private JPAApi jpaApi;

    @Inject
    public JPAMemeContext(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    protected EntityManager getEntityManager() {
        return this.jpaApi.em();
    }

    @Override
    public void save(Meme m) {
        getEntityManager().persist(m);
    }

    @Override
    public List<Meme> findAll() {
        return getEntityManager().createNamedQuery("Meme.getAll", Meme.class)
                .getResultList();
    }
    }
