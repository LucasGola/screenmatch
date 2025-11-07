package br.com.screenmatch.main;

import br.com.screenmatch.models.Episode;
import br.com.screenmatch.models.SeasonData;
import br.com.screenmatch.models.Serie;
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
    private List<Serie> serieDataList = new ArrayList<>();

    public void showMenu() {
        int option;

        do {
            String menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar series
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    searchSerie();
                    break;
                case 2:
                    searchEpisode();
                    break;
                case 3:
                    displaySeries();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (option != 0);
    }

    private void searchSerie() {
        SerieData data = getSerieData();
        serieDataList.add(new Serie(data));
        System.out.println(data);
    }

    private SerieData getSerieData() {
        System.out.println("Qual série deseja buscar?");
        String title = sc.nextLine();
        var json = apiRequests.getData(URL_BASE + title.replace(" ", "+") + API_KEY);
        return parser.getData(json, SerieData.class);
    }

    private void searchEpisode() {
        SerieData serieData = getSerieData();
        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var json = apiRequests.getData(URL_BASE + serieData.title().replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = parser.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);
    }

    private void displaySeries() {
        serieDataList.forEach(System.out::println);
    }
}
