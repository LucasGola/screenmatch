package br.com.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieData(@JsonAlias("Title") String title,
                        int totalSeasons,
                        @JsonAlias("Released") String releasedDate,
                        @JsonAlias("imdbRating") Double rating,
                        @JsonAlias("imdbVotes") String totalVotes,
                        @JsonAlias("Genre") String genre,
                        @JsonAlias("Actors") String actors,
                        @JsonAlias("Plot") String plot,
                        @JsonAlias("Poster") String poster) {
}
