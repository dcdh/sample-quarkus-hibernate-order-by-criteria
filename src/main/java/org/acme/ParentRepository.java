package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.List;

@ApplicationScoped
public class ParentRepository {

    @Inject
    EntityManager em;

    public List<Parent> queryParentOrderByChildId() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Parent> cq = cb.createQuery(Parent.class);
        Root<Parent> root = cq.from(Parent.class);
        root.fetch("child", JoinType.LEFT);
        Join<Parent, Child> child = root.join("child", JoinType.LEFT);
        cq.select(root).distinct(true);
        cq.orderBy(cb.desc(child.get("name")));
        TypedQuery<Parent> query = em.createQuery(cq);
        return query.getResultList();
    }
}
