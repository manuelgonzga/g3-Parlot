package es.Parlot.Language_Learning.controladores;

import es.Parlot.Language_Learning.modelo.*;
import es.Parlot.Language_Learning.modelo.enums.rol;
import es.Parlot.Language_Learning.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class InicioController {


    @Autowired
    ProfesorService profesorService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    MetodoPagoService metodoPagoService;


    @RequestMapping("/find-teacher")
    public String findTeacher() {
        return "EncuentraProfesor";
    }

    @RequestMapping("/")
    public String defaultPage() {
        return "Inicio";
    }

    @RequestMapping("/login")
    public String iniciarSesion() {
        return "login";
    }


    @ModelAttribute("isAuthenticatedAdmin")
    public boolean isAuthenticatedAdmin(Principal principal) {
        if (principal == null) {
            return false;
        }
        Usuario usuario = usuarioService.getByUsername(principal.getName());
        return usuario.getRole().equals(rol.ROLE_ADMIN);
    }

    @ModelAttribute("isAuthenticatedStudent")
    public boolean isAuthenticatedStudent(Principal principal) {
        if (principal == null) {
            return true;
        }
        Usuario usuario = usuarioService.getByUsername(principal.getName());
        return usuario.getRole().equals(rol.ROLE_STUDENT);
    }

    @ModelAttribute("isAuthenticated")
    public boolean isAuthenticated(Principal principal) {
        return principal != null;
    }

    @RequestMapping("/logout")
    public String cerrarSesion() {
        return "Inicio";
    }

    @RequestMapping("/IniciarSesion")
    public String InicioSesion(Principal principal) {
        return "Inicio";
    }

    @ModelAttribute("usuario")
    public Usuario getUser(Principal principal) {
        if (principal == null) {
            return null;
        }
        return usuarioService.getByUsername(principal.getName());
    }

    @GetMapping("/perfil/{id}")
    public String perfil(Model model, @PathVariable int id) {
        Usuario usuario = usuarioService.getById(id);
        if (usuario.getRole().equals(rol.ROLE_TEACHER)) {
            model.addAttribute("profesor", profesorService.getProfesorById(id));
            String videoPath = profesorService.getVideoPathByProfesorId(id);
            model.addAttribute("videoPath", videoPath);
            String fotoPath = usuarioService.getFotoPathByUsuarioId(id);
            model.addAttribute("fotoPath", fotoPath);
            return "PerfilProfesor";
        } else if (usuario.getRole().equals(rol.ROLE_STUDENT)) {
            model.addAttribute("alumno", alumnoService.getAlumnoById(id));
            String fotoPath = usuarioService.getFotoPathByUsuarioId(id);
            model.addAttribute("fotoPath", fotoPath);
            return "PerfilAlumno";
        }

        return "Inicio";
    }

    @ModelAttribute("fotoPath")
    public String fotoPath(Principal principal) {
        if (principal == null) {
            return null;
        }
        Usuario usuario = usuarioService.getByUsername(principal.getName());
        return usuario.getFotoPerfilPath();
    }

    @ModelAttribute("tieneFoto")
    public boolean tieneFoto(Principal principal) {
        if (principal == null) {
            return false;
        }
        Usuario usuario = usuarioService.getByUsername(principal.getName());
        return usuario.getFotoPerfilPath() != null;
    }

    @ModelAttribute("saldo")
    public String getSaldo(Principal principal) {
        if (principal == null) {
            return null;
        } else {
            Usuario usuario = usuarioService.getByUsername(principal.getName());
            return usuario.getSaldo().toString();
        }
    }

    @ModelAttribute("Nombre")
    public String getNombreyApellidos(Principal principal) {
        if (principal == null) {
            return null;
        } else {
            Usuario usuario = usuarioService.getByUsername(principal.getName());
            return usuario.getName();
        }
    }


    @ModelAttribute("profesores")
    public List<Profesor> listaProfesores() {
        return profesorService.getAll();
    }

    @Autowired
    PaisService paisService;

    @ModelAttribute("paises")
    public List<Pais> listaPaises() {
        return paisService.getAll();
    }

    @Autowired
    TipoClaseService tipoClaseService;

    @ModelAttribute("tipos")
    public List<Clasetipo> listaTipos() {
        return tipoClaseService.getAll();
    }

    @Autowired
    IdiomaService idiomaService;

    @ModelAttribute("idiomas")
    public List<Idioma> listaIdiomas() {
        return idiomaService.getAll();
    }

    @ModelAttribute("usuarios")
    public List<Usuario> listaUsuarios() {
        return usuarioService.getAll();
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/editProfile/{id}")
    public String editProfile(Model model, @PathVariable int id) {
        Usuario usuario = usuarioService.getById(id);
        if (usuario.getRole().equals(rol.ROLE_TEACHER)) {
            model.addAttribute("profesor", profesorService.getProfesorById(id));
            String videoPath = profesorService.getVideoPathByProfesorId(id);
            model.addAttribute("videoPath", videoPath);
            String fotoPath = usuarioService.getFotoPathByUsuarioId(id);
            model.addAttribute("fotoPath", fotoPath);
            return "EditarPerfilProfesor";
        } else if (usuario.getRole().equals(rol.ROLE_STUDENT)) {
            model.addAttribute("student", alumnoService.getAlumnoById(id));
            String fotoPath = usuarioService.getFotoPathByUsuarioId(id);
            model.addAttribute("fotoPath", fotoPath);
            return "EditarPerfilAlumno";
        }

        return "Inicio";
    }

    @GetMapping("/Monedero/{id}")
    public String monedero(Model model, @PathVariable int id) {
        return "Monedero";
    }

    @GetMapping("/clases/{id}")
    public String clases(Model model, @PathVariable int id) {
        Usuario usuario = usuarioService.getById(id);
        if (usuario.getRole().equals(rol.ROLE_TEACHER)) {
            model.addAttribute("profesor", profesorService.getProfesorById(id));
            String fotoPath = usuarioService.getFotoPathByUsuarioId(id);
            model.addAttribute("fotoPath", fotoPath);
            return "clasesProfesor";
        } else if (usuario.getRole().equals(rol.ROLE_STUDENT)) {
            model.addAttribute("alumno", alumnoService.getAlumnoById(id));
            String fotoPath = usuarioService.getFotoPathByUsuarioId(id);
            model.addAttribute("fotoPath", fotoPath);
            return "clasesAlumno";
        }

        return "Inicio";
    }

    @ModelAttribute("MetodosPago")
    public Set<MetodoDePago> MetodosPago(Principal principal){
        if(principal!=null) {
            Usuario user = usuarioService.getByUsername(principal.getName());
            return user.getMetodoDePagos();
        }
            return new HashSet<>();

    }

    @RequestMapping("/addPayMethodFromMonedero/{userId}")
    public String DesdePago(Model model, @PathVariable Integer userId){
        model.addAttribute("metodoPago", new MetodoDePago());
        model.addAttribute("usuario", usuarioService.getById(userId));
        return "MetodoPagoRegistro";
    }

    @PostMapping("/introducir-saldo")
    public String introducirSaldo(@ModelAttribute("usuario")Usuario usuario, Model model){
        Double saldoToAdd = usuario.getSaldoToAdd();
        usuario.addSaldo(saldoToAdd);
        usuarioService.introducirSaldo(usuario, usuario.getSaldo());
        model.addAttribute("MetodosPago", usuario.getMetodoDePagos());
        return "redirect:/Monedero/" + usuario.getId();
    }


}





