package br.com.screenmatch.models;

import jakarta.persistence.*;

@Entity
@Table(name = "testeJava")
public class TesteJava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int age;
    private String address;

    public TesteJava() {};
}
