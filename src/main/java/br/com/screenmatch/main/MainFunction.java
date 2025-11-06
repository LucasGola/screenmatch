package br.com.screenmatch.main;

import br.com.screenmatch.models.Episode;
import br.com.screenmatch.models.SeasonData;
import br.com.screenmatch.models.SerieData;
import br.com.screenmatch.service.ApiRequests;
import br.com.screenmatch.service.ParseData;

import java.util.*;
import java.util.stream.Collectors;

public class MainFunction {
    private Scanner sc = new Scanner(System.in);
    private ApiRequests apiRequests = new ApiRequests();
    private ParseData parser = new ParseData();
    private List<SeasonData> seasons = new ArrayList<>();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=5c7784a4";

    public void showMenu() {
        System.out.print("Digite o Título que deseja buscar: ");
        var title = sc.nextLine();
        var json = apiRequests.getData(URL_BASE + title.replace(" ", "+") + API_KEY);
        SerieData data = parser.getData(json, SerieData.class);
        System.out.println(data);

        for (int i = 1; i<=data.totalSeasons(); i++) {
            json = apiRequests.getData(URL_BASE + title.replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = parser.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }

        seasons.forEach(System.out::println);

        List<Episode> episodeList = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(e -> new Episode(s.season(), e))
                ).toList();

//        System.out.println("Qual episódio está buscando?");
//        var search = sc.nextLine();
//        Optional<Episode> findedEpisode = episodeList.stream()
//                .filter(e -> e.getTitle().toUpperCase().contains(search.toUpperCase()))
//                .findAny();
//
//        if (findedEpisode.isPresent()) {
//            System.out.println("Episódio encontrado!");
//            System.out.println("Temporada: " + findedEpisode.get().getSeason());
//        } else {
//            System.out.println("Nenhum episódio encontrado");
//        }

        Map<Integer, Double> ratingPerSeason = episodeList.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason,
                        Collectors.averagingDouble(Episode::getRating)));
        System.out.println(ratingPerSeason);

        DoubleSummaryStatistics est = episodeList.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getRating));
        System.out.println(est);
    }
}
