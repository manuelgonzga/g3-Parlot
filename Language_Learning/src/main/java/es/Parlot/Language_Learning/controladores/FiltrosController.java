package es.Parlot.Language_Learning.controladores;

import es.Parlot.Language_Learning.modelo.Pais;
import es.Parlot.Language_Learning.modelo.Profesor;
import es.Parlot.Language_Learning.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FiltrosController {

    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IdiomaService idiomaService;

    @Autowired
    private TipoClaseService tipoClaseService;




    @PostMapping("/consulta")
    public String getProfesores(Model model, @RequestParam(required = false) Integer pais, @RequestParam(required = false) Double max, @RequestParam(required = false) Double min, @RequestParam(required = false) Integer tipo, @RequestParam(required = false) Integer idiomaHabla, @RequestParam(required = false) Integer idiomaEnseña) {
        List<Profesor> profesores = profesorService.buscarProfesores(pais, max, min, tipo, idiomaHabla, idiomaEnseña);
        model.addAttribute("profesores", profesores);
        model.addAttribute("paises", listaPaises());
        model.addAttribute("idiomas", idiomaService.getAll());
        model.addAttribute("tipos", tipoClaseService.getAll());

        return "EncuentraProfesor";
    }

    @GetMapping("/profesor/{id}")
    public String getProfesor(@PathVariable Integer id, Model model){
        model.addAttribute("profesor", profesorService.getProfesorById(id));
        String videoPath = profesorService.getVideoPathByProfesorId(id);
        model.addAttribute("videoPath", videoPath);
        String fotoPath = usuarioService.getFotoPathByUsuarioId(id);
        model.addAttribute("fotoPath", fotoPath);
        return "PerfilProfesor";
    }

    @Autowired
    PaisService paisService;

    @ModelAttribute("paises")
    public List<Pais> listaPaises(){
        return paisService.getAll();
    }



}