package org.acme;

import jakarta.persistence.*;

@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARENT_ID")
    Long id;

    @OneToOne
    @JoinColumn(name = "ID_CHILD")
    Child child;

    String name;

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", child=" + child +
                ", name='" + name + '\'' +
                '}';
    }
}
