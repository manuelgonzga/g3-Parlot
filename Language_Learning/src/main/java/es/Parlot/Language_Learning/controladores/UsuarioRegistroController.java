package es.Parlot.Language_Learning.controladores;

import org.springframework.stereotype.Controller;

@Controller
public class UsuarioRegistroController {

}
  /*  @Autowired
    UsuarioService usuarioService;

    @Autowired
    PaisServicio paisServicio;

    // Crear un objeto vacío para que se le asignen los valores a traves de la vista gracias al metodo saveUsuario()
    @ModelAttribute("alumno")
    public Alumno User() {
        return new Alumno(); // Crea un objeto vacío
    }

    @ModelAttribute("paises")
    public List<Pais> listaPaises(){
        return paisServicio.getAll();
    }

    @ModelAttribute("profesor")
    public Profesor Profesor() {
        return new Profesor(); // Crea un objeto vacío
    }
    // Guardar el usuario en la base de datos después de invocar al metodo usuarioEnity() para que cree el objeto al que se le asignarán los valores a traves de la vista
    @PostMapping("/registrar-usuario")
    public String saveUsuario(@ModelAttribute("alumno") Alumno alumno){
        usuarioService.save(alumno);
        return "redirect:/login";
    }

    @PostMapping("/registrar-profesor")
    public String saveProfesor(@ModelAttribute("profesor") Profesor profesor){
        usuarioService.save(profesor);
        return "redirect:/login";
    }

    @RequestMapping("/sign-up")
    public String signUp() {
        return "Registro";
    }

    @RequestMapping("/sign-up-teacher")
    public String signUpTeacher(){
        return "RegistroProfesor";
    }

    //NEW
    @RequestMapping("/find-teacher")
    public String findTeacher(){
        return "EncuentraProfesor";
    }



    //Mostrar la pagina de iniciar sesion por defecto
    @RequestMapping("/")
    public String defaultPage() {
        return "Inicio";
    }

    //Mostrar la pagina de iniciar sesion
    @RequestMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    @RequestMapping("/SignInExito")
    public String InicioSesionExito(){
        return "InicioSesionExito";
    }*/
