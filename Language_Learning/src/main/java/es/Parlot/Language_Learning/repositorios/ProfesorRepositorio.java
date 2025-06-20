package es.Parlot.Language_Learning.repositorios;

import es.Parlot.Language_Learning.modelo.Profesor;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ProfesorRepositorio extends JpaRepository<Profesor, Integer>, JpaSpecificationExecutor<Profesor> {


}