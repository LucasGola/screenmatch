package br.com.screenmatch.repository;

import br.com.screenmatch.models.Serie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    @EntityGraph(attributePaths = {"genre", "actors"})
    @Query("SELECT s FROM Serie s")
    List<Serie> findAllWithGenresAndActors();
}
