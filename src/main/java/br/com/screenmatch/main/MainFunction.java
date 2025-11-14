package br.com.screenmatch.main;

import br.com.screenmatch.models.SeasonData;
import br.com.screenmatch.models.Serie;
import br.com.screenmatch.models.SerieData;
import br.com.screenmatch.repository.ActorRepository;
import br.com.screenmatch.repository.GenreRepository;
import br.com.screenmatch.repository.SerieRepository;
import br.com.screenmatch.service.ApiRequests;
import br.com.screenmatch.service.ParseData;
import br.com.screenmatch.service.SerieFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainFunction {
    private final Scanner SC = new Scanner(System.in);
    private final ApiRequests API_REQUESTS = new ApiRequests();
    private ParseData parser = new ParseData();
    private List<SeasonData> seasons = new ArrayList<>();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=5c7784a4";
    private List<Serie> seriesList = new ArrayList<>();
    private SerieRepository serieRepository;
    private GenreRepository genreRepository;
    private ActorRepository actorRepository;


    public MainFunction(SerieRepository serieRepository,
                        GenreRepository genreRepository,
                        ActorRepository actorRepository) {
        this.serieRepository = serieRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
    }


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
            option = SC.nextInt();
            SC.nextLine();

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
        SerieData serieData = getSerieData();
        SerieFactory serieFactory = new SerieFactory(genreRepository, actorRepository);
        Serie serie = serieFactory.fromData(serieData);
        serieRepository.save(serie);
        System.out.println(serieData);
    }

    private SerieData getSerieData() {
        System.out.println("Qual série deseja buscar?");
        String title = SC.nextLine();
        var json = API_REQUESTS.getData(URL_BASE + title.replace(" ", "+") + API_KEY);
        return parser.getData(json, SerieData.class);
    }

    private void searchEpisode() {
        SerieData serieData = getSerieData();
        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            var json = API_REQUESTS.getData(URL_BASE + serieData.title().replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = parser.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);
    }

    private void displaySeries() {
        serieRepository.findAllWithGenresAndActors().stream()
                .sorted(Comparator.comparing(Serie::getTitle))
                .forEach(System.out::println);
    }
}
