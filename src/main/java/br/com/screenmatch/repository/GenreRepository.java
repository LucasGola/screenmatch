package br.com.screenmatch.repository;

import br.com.screenmatch.models.Genre;
import br.com.screenmatch.types.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByCode(Genres code);
}
