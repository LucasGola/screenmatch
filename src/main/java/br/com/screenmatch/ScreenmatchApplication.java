package br.com.screenmatch;

import br.com.screenmatch.main.MainFunction;
import br.com.screenmatch.repository.ActorRepository;
import br.com.screenmatch.repository.GenreRepository;
import br.com.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "br.com.screenmatch.models")
@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	@Autowired
	private SerieRepository serieRepository;
	private GenreRepository genreRepository;
	private ActorRepository actorRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MainFunction mainFunction = new MainFunction(serieRepository, genreRepository, actorRepository);
		mainFunction.showMenu();
	}
}
