package es.Parlot.Language_Learning.controladores;

import es.Parlot.Language_Learning.modelo.*;
import es.Parlot.Language_Learning.modelo.enums.rol;
import es.Parlot.Language_Learning.servicios.IdiomaService;
import es.Parlot.Language_Learning.servicios.PaisService;
import es.Parlot.Language_Learning.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
public class AlumnoRegistroController {

    @PostMapping("/edit/usuario/{id}")
    public String editUser(@PathVariable Integer id, Usuario updatedUser, Model model) {
        updatedUser.setId(id);
        usuarioService.update(updatedUser);
        model.addAttribute("usuario", updatedUser);

        return "redirect:/logout";
    }
    @Autowired
    UsuarioService usuarioService;

    // Crear un objeto vacío para que se le asignen los valores a traves de la vista gracias al metodo saveUsuario()
    @ModelAttribute("alumno")
    public Usuario User() {
        FabricaUsuario fabricaUsuario = new FabricaUsuario();
        return fabricaUsuario.getUsuario(rol.ROLE_STUDENT);
    }



    // Guardar el usuario en la base de datos después de invocar al metodo usuarioEnity() para que cree el objeto al que se le asignarán los valores a traves de la vista
    @PostMapping("/registrar-usuario")
    public String saveAlumno(@ModelAttribute("alumno") Alumno alumno, @ModelAttribute("imagen") MultipartFile imagen){
        alumno.setRole(rol.ROLE_STUDENT);
        saveImagenPerfil(imagen,alumno);
        usuarioService.save(alumno);
        return "redirect:/login";
    }

    private void saveImagenPerfil(MultipartFile imagen, Alumno alumno) {
        // Guardar el video
        if (imagen != null && !imagen.isEmpty()) {
            // Generar un nombre de archivo único
            String uniqueFilename = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();

            // Definir la ruta del archivo
            // Definir la ruta del archivo
            Path rootPath = Paths.get("Language_Learning/src/main/resources/static/imagenFotoPerfilUpload").resolve(uniqueFilename).toAbsolutePath();

            // Guardar el archivo en el servidor
            // Guardar el archivo en el servidor
            try (InputStream inputStream = imagen.getInputStream()) {
                Files.copy(inputStream, rootPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            alumno.setFotoPerfilPath("../imagenFotoPerfilUpload/" + uniqueFilename);

        }
    }

    @RequestMapping("/sign-up")
    public String signUpAlumno() {
        return "Registro";
    }

    //PAISES

    @Autowired
    PaisService paisService;

    @ModelAttribute("paises")
    public List<Pais> listaPaises(){
        return paisService.getAll();
    }

    @Autowired
    IdiomaService idiomaService;

    @ModelAttribute("idiomas")
    public List<Idioma> idiomas(){
        return idiomaService.getAll();
    }





}