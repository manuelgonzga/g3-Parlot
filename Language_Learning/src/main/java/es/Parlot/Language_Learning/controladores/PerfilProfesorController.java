package es.Parlot.Language_Learning.controladores;

import es.Parlot.Language_Learning.modelo.Clase;
import es.Parlot.Language_Learning.servicios.AlumnoService;
import es.Parlot.Language_Learning.servicios.ClaseService;
import es.Parlot.Language_Learning.servicios.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class PerfilProfesorController {

    @Autowired
    ProfesorService profesorService;


    @PostMapping("/registrar-clase/profesor/{profesorId}")
    public String registrarClase(@PathVariable("profesorId") Integer profesorId, Model model){
        model.addAttribute("profesor", profesorService.getProfesorById(profesorId));
        return "ReservaClase";
    }
}
