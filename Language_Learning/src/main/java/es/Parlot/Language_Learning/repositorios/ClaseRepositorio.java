package es.Parlot.Language_Learning.repositorios;


import es.Parlot.Language_Learning.modelo.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseRepositorio extends JpaRepository<Clase, Integer> {

}
