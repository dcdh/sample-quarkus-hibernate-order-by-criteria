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

    public Parent() {
    }

    public Parent(Parent parent, Child child) {
        this.id = parent.id;
        this.name = parent.name;
        this.child = child;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", child=" + child +
                ", name='" + name + '\'' +
                '}';
    }
}
