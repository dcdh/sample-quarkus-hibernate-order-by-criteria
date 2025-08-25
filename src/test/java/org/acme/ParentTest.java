package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class ParentTest {

    @Inject
    ParentRepository parentRepository;

    @Test
    void shouldQueryParentOrderByChildId() {
        System.out.println(parentRepository.queryParentOrderByChildId());
    }
}
