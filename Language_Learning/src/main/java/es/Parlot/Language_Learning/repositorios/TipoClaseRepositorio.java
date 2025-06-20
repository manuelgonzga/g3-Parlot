package es.Parlot.Language_Learning.repositorios;

import es.Parlot.Language_Learning.modelo.Clasetipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoClaseRepositorio extends JpaRepository<Clasetipo, Integer> {

}
