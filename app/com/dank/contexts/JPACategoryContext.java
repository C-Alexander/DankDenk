package com.dank.contexts;

import com.dank.entities.Category;
import com.dank.entities.Meme;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class JPACategoryContext implements CategoryContext {

    private JPAApi jpaApi;

    @Inject
    public JPACategoryContext(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    protected EntityManager getEntityManager() {
        return this.jpaApi.em();
    }

    @Override
    public Category getByName(String name) {
        Query q = getEntityManager().createNamedQuery("Category.findByName", Category.class);
        q.setParameter("name", name);
        return (Category) q.getSingleResult();
    }
}
