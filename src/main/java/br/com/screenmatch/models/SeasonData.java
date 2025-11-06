package br.com.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record SeasonData(@JsonAlias("Season") int season,
                         @JsonAlias("Episodes") List<EpisodeData> episodes) {
}
