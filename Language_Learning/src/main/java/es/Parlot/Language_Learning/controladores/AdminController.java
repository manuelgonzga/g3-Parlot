package es.Parlot.Language_Learning.controladores;

import es.Parlot.Language_Learning.modelo.*;
import es.Parlot.Language_Learning.modelo.enums.rol;
import es.Parlot.Language_Learning.servicios.ClaseService;
import es.Parlot.Language_Learning.servicios.IdiomaService;
import es.Parlot.Language_Learning.servicios.PaisService;
import es.Parlot.Language_Learning.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClaseService claseService;

    @PostMapping("/admin/deleteUser/usuario/{id}")
    public String deleteUser(@PathVariable Integer id) {
        usuarioService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteClase/clase/{id}")
    public void deleteClase(@PathVariable Integer id) {
        claseService.delete(id);
    }
    

    @GetMapping("/admin/editUser/usuario/{id}")
    public String editUser(@PathVariable Integer id, Model model) {

            Usuario user = usuarioService.getById(id);
            if (user.getRole().equals(rol.ROLE_TEACHER)) {
                Profesor profesor = (Profesor) user;
                model.addAttribute("esProfesor", "true");
                model.addAttribute("profesor", profesor);
                model.addAttribute("clases", profesor.getClases());
            } else if (user.getRole().equals(rol.ROLE_STUDENT)) {
                Alumno alumno = (Alumno) user;
                model.addAttribute("EsAlumno", "true");
                model.addAttribute("alumno", alumno);
            }
            model.addAttribute("usuario", usuarioService.getById(id));
        return "admin/EditarUsuario";
    }

    @PostMapping("/admin/edit/usuario/{id}")
    public String editUser(@PathVariable Integer id, Usuario updatedUser) {
        updatedUser.setId(id);
        usuarioService.update(updatedUser);
        return "redirect:/admin";
    }

    @Autowired
    PaisService paisService;

    @ModelAttribute("paises")
    public List<Pais> paises() {
        return paisService.getAll();
    }

    @Autowired
    IdiomaService idiomaService;

    @ModelAttribute("idiomas")
    public List<Idioma> idiomas() {
        return idiomaService.getAll();
    }

}
