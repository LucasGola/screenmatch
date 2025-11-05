package br.com.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.ArrayList;

public record SeasonData(@JsonAlias("Season") int season,
                         @JsonAlias("Episodes") ArrayList<EpisodeData> episodes) {
}
