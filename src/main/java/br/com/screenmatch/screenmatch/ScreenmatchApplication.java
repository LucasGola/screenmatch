package br.com.screenmatch.screenmatch;

import br.com.screenmatch.models.EpisodeData;
import br.com.screenmatch.models.SeasonData;
import br.com.screenmatch.models.SerieData;
import br.com.screenmatch.service.ApiRequests;
import br.com.screenmatch.service.ParseData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		// http://www.omdbapi.com/?i=tt3896198&apikey=5c7784a4
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Olá mundo");

		var apiRequests = new ApiRequests();
		var json = apiRequests.getData("http://www.omdbapi.com/?t=dark&apikey=5c7784a4");
		System.out.println(json);

		ParseData parser = new ParseData();
		SerieData data = parser.getData(json, SerieData.class);
		System.out.println(data);

		json = apiRequests.getData("https://www.omdbapi.com/?t=dark&season=1&apikey=5c7784a4");
		SeasonData seasonData = parser.getData(json, SeasonData.class);
		System.out.println(seasonData);
	}
}
