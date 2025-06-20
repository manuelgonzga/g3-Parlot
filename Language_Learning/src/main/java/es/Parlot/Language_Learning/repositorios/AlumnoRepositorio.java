package es.Parlot.Language_Learning.repositorios;

import es.Parlot.Language_Learning.modelo.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepositorio extends JpaRepository<Alumno, Integer>{
    Alumno findByusername(String username);
}
