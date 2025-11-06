package br.com.screenmatch.models;

import java.util.List;

public class Serie {
    private String title;
    private int totalSeasons;
    private List<SeasonData> seasons;
    private String releasedDate;

    public void Serie(String title, int totalSeasons, List<SeasonData> seasons, String releasedDate) {
        this.title = title;
        this.totalSeasons = totalSeasons;
        this.seasons = seasons;
        this.releasedDate = releasedDate;
    }
}
