package org.acme;

import jakarta.persistence.*;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHILD_ID")
    private Long id;

    private String name;
}
