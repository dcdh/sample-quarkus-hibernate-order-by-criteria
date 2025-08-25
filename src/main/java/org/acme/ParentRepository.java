package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.List;

@ApplicationScoped
public class ParentRepository {

    @Inject
    EntityManager em;

    public List<Parent> queryParentOrderByChildId() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Parent> cq = cb.createQuery(Parent.class);
        Root<Parent> root = cq.from(Parent.class);
        Join<Parent, Child> childJoin = root.join("child");
        cq.distinct(true);
        cq.orderBy(cb.asc(childJoin.get("id")));
        TypedQuery<Parent> query = em.createQuery(cq);
        List<Parent> resultList = query.getResultList();
        return resultList;
    }
}
