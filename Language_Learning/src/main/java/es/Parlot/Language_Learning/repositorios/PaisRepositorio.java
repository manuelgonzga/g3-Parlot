package es.Parlot.Language_Learning.repositorios;

import es.Parlot.Language_Learning.modelo.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepositorio extends JpaRepository<Pais, Integer>{

}
