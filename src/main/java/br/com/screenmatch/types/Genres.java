package br.com.screenmatch.types;

import java.util.Arrays;
import java.util.List;

public enum Genres {

    ACAO("Action"),
    AVENTURA("Adventure"),
    ANIMACAO("Animation"),
    COMEDIA("Comedy"),
    CRIME("Crime"),
    DOCUMENTARIO("Documentary"),
    DRAMA("Drama"),
    FAMILIA("Family"),
    FANTASIA("Fantasy"),
    HISTORIA("History"),
    TERROR("Horror"),
    MUSICA("Music"),
    MUSICAL("Musical"),
    MISTERIO("Mystery"),
    ROMANCE("Romance"),
    FICCAO_CIENTIFICA("Science Fiction", "Sci-Fi"),
    SUSPENSE("Thriller"),
    GUERRA("War"),
    OESTE("Western");

    private final List<String> foreignNames;

    Genres(String... foreignNames) {
        this.foreignNames = Arrays.asList(foreignNames);
    }

    public boolean matches(String name) {
        if (name == null) return false;
        String normalized = name.trim().toLowerCase();
        return foreignNames.stream().anyMatch(f -> f.toLowerCase().equals(normalized));
    }

    public static Genres fromForeignName(String name) {
        if (name == null || name.isBlank()) return null;
        return Arrays.stream(values())
                .filter(g -> g.matches(name))
                .findFirst()
                .orElse(null);
    }
}
