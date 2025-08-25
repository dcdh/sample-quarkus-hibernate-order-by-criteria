package org.acme;

import jakarta.persistence.*;

@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARENT_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ID_CHILD")
    private Child child;

}
