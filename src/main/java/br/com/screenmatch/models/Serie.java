package br.com.screenmatch.models;

import java.util.ArrayList;

public class Serie {
    private String title;
    private int totalSeasons;
    private ArrayList<SeasonData> seasons;
    private String releasedDate;

    public void Serie(String title, int totalSeasons, ArrayList<SeasonData> seasons, String releasedDate) {
        this.title = title;
        this.totalSeasons = totalSeasons;
        this.seasons = seasons;
        this.releasedDate = releasedDate;
    }
}
