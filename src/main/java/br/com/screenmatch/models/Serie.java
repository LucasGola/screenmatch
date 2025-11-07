package br.com.screenmatch.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class Serie {
    private String title;
    private Integer totalSeasons;
    private List<SeasonData> seasons;
    private LocalDate releasedDate;
    private Double rating;
    private Integer totalVotes;
    private List<String> genre = new ArrayList<>();
    private List<String> actors = new ArrayList<>();
    private String plot;
    private String poster;

//    public void Serie(String title, int totalSeasons, List<SeasonData> seasons, LocalDate releasedDate, List<String> genre, List<String> actors, String plot, String porter) {
//        this.title = title;
//        this.totalSeasons = totalSeasons;
//        this.seasons = seasons;
//        this.releasedDate = releasedDate;
//        this.genre = genre;
//        this.actors = actors;
//        this.plot = plot;
//        this.poster = poster;
//    }

    public Serie(SerieData serieData) {
        this.title = serieData.title();
        this.totalSeasons = serieData.totalSeasons();

        try {
            this.releasedDate = LocalDate.parse(serieData.releasedDate(), DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
        } catch (DateTimeParseException ex) {
            this.releasedDate = null;
        }

        this.rating = serieData.rating();

        try {
            this.totalVotes = Integer.valueOf(serieData.totalVotes().replace(",", ""));
        } catch (NumberFormatException ex) {
            this.totalVotes = null;
        }

        try {
            this.genre = Stream.of(serieData.genre().split("\\s*,\\s*"))
                    .map(String::trim)
                    .toList();
        } catch (NullPointerException ex) {
            this.genre = null;
        }

        try {
            this.actors = Stream.of(serieData.actors().split("\\s*,\\s*"))
                    .map(String::trim)
                    .toList();;
        } catch (NullPointerException ex) {
            this.actors = null;
        }

        this.plot = serieData.plot();
        this.poster = serieData.poster();
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", seasons=" + seasons +
                ", releasedDate=" + releasedDate +
                ", rating=" + rating +
                ", totalVotes=" + totalVotes +
                ", genre=" + genre +
                ", actors=" + actors +
                ", plot='" + plot + '\'' +
                ", poster='" + poster;
    }
}
