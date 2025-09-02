package org.acme;

import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

@QuarkusTest
class ParentTest {

    @Inject
    ParentRepository parentRepository;

    @Inject
    EntityManager em;

    @BeforeEach
    void setup() {
        QuarkusTransaction.requiringNew().run(() -> {
            Parent napoleon = new Parent();
            napoleon.name = "Napoleon";
            em.persist(napoleon);
            Child napoleon2 = new Child();
            napoleon2.name = "Napoleon II";
            em.persist(napoleon2);
            napoleon.child = napoleon2;
            em.merge(napoleon);

            Parent louis17 = new Parent();
            louis17.name = "Louis 17";
            em.persist(louis17);

            Parent louis14 = new Parent();
            louis14.name = "Louis 14";
            em.persist(louis14);
            Child louis15 = new Child();
            louis15.name = "Louis 15";
            em.persist(louis15);
            louis17.child = louis15;
            em.merge(louis17);
        });
    }

    @AfterEach
    void tearDown() {
        QuarkusTransaction.requiringNew().run(() -> {
            em.createQuery("DELETE FROM Parent").executeUpdate();
            em.createQuery("DELETE FROM Child").executeUpdate();
        });
    }

    @Test
    void shouldQueryParentOrderByChildId() {
        final List<Parent> parents = parentRepository.queryParentOrderByChildId();
        Assertions.assertFalse(parents.isEmpty());
        System.out.println(parents);
    }
}
