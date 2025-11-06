package br.com.screenmatch.models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer season;
    private String title;
    private Integer episode;
    private Double rating;
    private LocalDate released;

    public Episode(int season, EpisodeData e) {
        this.season = season;
        this.title = e.title();
        this.episode = e.episode();

        try {
            this.rating = Double.valueOf(e.rating());
        } catch (NumberFormatException ex) {
            this.rating = 0.0;
        }

        try {
            this.released = LocalDate.parse(e.released());
        } catch (DateTimeParseException ex) {
            this.released = null;
        }
    }

    @Override
    public String toString() {
        return "season=" + season +
                ", title='" + title + '\'' +
                ", episode=" + episode +
                ", rating=" + rating +
                ", released=" + released;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSeason() {
        return this.season;
    }

    public double getRating() {
        return this.rating;
    }
}
