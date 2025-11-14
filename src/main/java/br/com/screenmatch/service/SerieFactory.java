package br.com.screenmatch.service;

import br.com.screenmatch.models.Serie;
import br.com.screenmatch.models.SerieData;
import br.com.screenmatch.repository.ActorRepository;
import br.com.screenmatch.repository.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class SerieFactory {

    private final GenreRepository genreRepo;
    private final ActorRepository actorRepo;

    public SerieFactory(GenreRepository genreRepo, ActorRepository actorRepo) {
        this.genreRepo = genreRepo;
        this.actorRepo = actorRepo;
    }

    public Serie fromData(SerieData data) {
        return new Serie(data, genreRepo, actorRepo);
    }
}
