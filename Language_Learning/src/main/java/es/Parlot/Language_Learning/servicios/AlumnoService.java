package es.Parlot.Language_Learning.servicios;

import es.Parlot.Language_Learning.modelo.Alumno;
import es.Parlot.Language_Learning.repositorios.AlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    @Autowired
    private UsuarioService Usuario;

    public Alumno getAlumnoById(Integer id) {
        return alumnoRepositorio.findById(id).get();
    }

    public Alumno getAlumnoByUser(String user) {
        return getAlumnoById(Usuario.getByUsername(user).getId());
    }

    public void update(Alumno updatedAlumno) {
        // Step 1: Retrieve the existing Alumno from the database
        Alumno existingAlumno = alumnoRepositorio.findById(updatedAlumno.getId())
                .orElseThrow(() -> new IllegalArgumentException("Alumno not found with ID: " + updatedAlumno.getId()));

        // Step 2: Update the existing Alumno's properties
        existingAlumno.setName(updatedAlumno.getName());
        existingAlumno.setMail((updatedAlumno.getMail()));
        // Add other fields to be updated as necessary

        // Step 3: Save the updated Alumno back to the database
        alumnoRepositorio.save(existingAlumno);
    }
}
