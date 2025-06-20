package es.Parlot.Language_Learning.controladores;

import es.Parlot.Language_Learning.servicios.ClaseService;
import es.Parlot.Language_Learning.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/deleteClase/clase/{id}")
    public String deleteClase(@PathVariable Integer id, Principal principal) {
        claseService.delete(id);
        int personaId = usuarioService.getByUsername(principal.getName()).getId();
        return "redirect:/clases/"+personaId;
    }

}
