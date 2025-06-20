package es.Parlot.Language_Learning.controladores;

import es.Parlot.Language_Learning.modelo.*;
import es.Parlot.Language_Learning.modelo.enums.rol;
import es.Parlot.Language_Learning.servicios.IdiomaService;
import es.Parlot.Language_Learning.servicios.PaisService;
import es.Parlot.Language_Learning.servicios.TipoClaseService;
import es.Parlot.Language_Learning.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
public class ProfesorRegistroController {
    @Autowired
    UsuarioService usuarioService;


    @ModelAttribute("profesor")
    public Profesor User() {
        FabricaUsuario fabricaUsuario = new FabricaUsuario();
        return (Profesor) fabricaUsuario.getUsuario(rol.ROLE_TEACHER);
    }

    @PostMapping("/registrar-profesor")
    public String saveProfesor(@ModelAttribute("profesor") Profesor profesor, @RequestParam("archivo") MultipartFile archivo, @RequestParam("video") MultipartFile video, @RequestParam("imagen") MultipartFile imagen){
        if (!archivo.isEmpty()) {
            profesor.setCertificado(true);
        }
        profesor.setRole(rol.ROLE_TEACHER);

        saveVideo(video,profesor);
        saveImagenPerfil(imagen,profesor);

        usuarioService.save(profesor);
        return "redirect:/login";
    }

    private void saveImagenPerfil(MultipartFile imagen, Profesor profesor) {
        if (!imagen.isEmpty()) {
            String uniqueFilename = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
            Path rootPath = Paths.get("Language_Learning/src/main/resources/static/imagenFotoPerfilUpload").resolve(uniqueFilename).toAbsolutePath();

            try (InputStream inputStream = imagen.getInputStream()) {
                Files.copy(inputStream, rootPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            profesor.setFotoPerfilPath("../imagenFotoPerfilUpload/" + uniqueFilename);

        }
    }

    private void saveVideo(MultipartFile video,Profesor profesor) {
        if (!video.isEmpty()) {
            String uniqueFilename = UUID.randomUUID().toString() + "_" + video.getOriginalFilename();


            Path rootPath = Paths.get("Language_Learning/src/main/resources/static/videoUploads").resolve(uniqueFilename).toAbsolutePath();


            try (InputStream inputStream = video.getInputStream()) {
                Files.copy(inputStream, rootPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            profesor.setVideoPath("../videoUploads/" + uniqueFilename);

        }
    }


    @RequestMapping("/sign-up-teacher")
    public String signUpTeacher(){
        return "RegistroProfesor";
    }

    //PAISES
    @Autowired
    PaisService paisService;

    @ModelAttribute("paises")
    public List<Pais> listaPaises(){
        return paisService.getAll();
    }


    @Autowired
    TipoClaseService tipoClaseService;

    @ModelAttribute("tiposClase")
    public List<Clasetipo> listaTipos(){
        return tipoClaseService.getAll();
    }


    @Autowired
    IdiomaService idiomaService;

    @ModelAttribute("idiomas")
    public List<Idioma> idiomas(){
        return idiomaService.getAll();
    }



}