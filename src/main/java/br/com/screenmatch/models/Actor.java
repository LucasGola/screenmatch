package br.com.screenmatch.models;

import jakarta.persistence.*;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    protected Actor() {}

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
