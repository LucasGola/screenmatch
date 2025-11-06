package br.com.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(@JsonAlias("Title") String title,
                          @JsonAlias("Episode") int episode,
                          @JsonAlias("imdbRating") String rating,
                          @JsonAlias("Released") String released) {

    @Override
    public String toString() {
        return title;
    }
}