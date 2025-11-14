package br.com.screenmatch.models;

import br.com.screenmatch.types.Genres;
import jakarta.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Genres code; // usa seu enum

    protected Genre() {}

    public Genre(Genres code) {
        this.code = code;
    }

    public Genres getCode() {
        return code;
    }
}
