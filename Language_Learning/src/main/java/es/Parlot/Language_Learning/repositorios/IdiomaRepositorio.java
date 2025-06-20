package es.Parlot.Language_Learning.repositorios;

import es.Parlot.Language_Learning.modelo.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepositorio extends JpaRepository<Idioma, Integer> {

}
