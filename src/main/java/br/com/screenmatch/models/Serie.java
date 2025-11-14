package br.com.screenmatch.models;

import br.com.screenmatch.repository.ActorRepository;
import br.com.screenmatch.repository.GenreRepository;
import br.com.screenmatch.types.Genres;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import br.com.screenmatch.models.Genre;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer totalSeasons;
    private LocalDate releasedDate;
    private Double rating;
    private Integer totalVotes;
    private String plot;
    private String poster;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "series_genres",
            joinColumns = @JoinColumn(name = "serie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "series_actors",
            joinColumns = @JoinColumn(name = "serie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();




    @Transient
    private List<SeasonData> seasons;

//    public Serie(String title, int totalSeasons, List<SeasonData> seasons, LocalDate releasedDate, List<String> genre, List<String> actors, String plot, String porter) {
//        this.title = title;
//        this.totalSeasons = totalSeasons;
//        this.seasons = seasons;
//        this.releasedDate = releasedDate;
//        this.genre = genre;
//        this.actors = actors;
//        this.plot = plot;
//        this.poster = poster;
//    }

    public Serie() {
    }

    public Serie(SerieData serieData,
                 GenreRepository genreRepo,
                 ActorRepository actorRepo) {

        this.title = serieData.title();
        this.totalSeasons = serieData.totalSeasons();

        try {
            this.releasedDate = LocalDate.parse(
                    serieData.releasedDate(),
                    DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)
            );
        } catch (Exception ex) {
            this.releasedDate = null;
        }

        this.rating = serieData.rating();
        this.totalVotes = Integer.valueOf(serieData.totalVotes().replace(",", ""));

        // -------------------------------
        // GÊNEROS
        // -------------------------------
        if (serieData.genre() != null) {
            Arrays.stream(serieData.genre().split(","))
                    .map(String::trim)
                    .map(Genres::fromForeignName)
                    .filter(Objects::nonNull)
                    .forEach(g -> {
                        Genre genre = genreRepo.findByCode(g)
                                .orElseGet(() -> genreRepo.save(new Genre(g)));
                        this.genres.add(genre);
                    });
        }

        // -------------------------------
        // ATORES
        // -------------------------------
        if (serieData.actors() != null) {
            Arrays.stream(serieData.actors().split(","))
                    .map(String::trim)
                    .forEach(actorName -> {
                        Actor actor = actorRepo.findByNameIgnoreCase(actorName)
                                .orElseGet(() -> actorRepo.save(new Actor(actorName)));
                        this.actors.add(actor);
                    });
        }

        this.plot = serieData.plot();
        this.poster = serieData.poster();
    }


    @Override
    public String toString() {
        return "title='" + title + '\'' + ", totalSeasons=" + totalSeasons + ", releasedDate=" + releasedDate + ", " +
                "genres=" + genres + ", actors=" + actors + ", rating=" + rating + ", totalVotes=" + totalVotes +
                ", plot='" + plot + '\'' + ", poster='" + poster + '\'';
    }

    public String getTitle() {
        return this.title;
    }
}