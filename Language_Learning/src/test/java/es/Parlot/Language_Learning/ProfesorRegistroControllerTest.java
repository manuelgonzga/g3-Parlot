package es.Parlot.Language_Learning;
import es.Parlot.Language_Learning.controladores.ProfesorRegistroController;
import es.Parlot.Language_Learning.modelo.Profesor;
import es.Parlot.Language_Learning.modelo.enums.rol;
import es.Parlot.Language_Learning.servicios.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfesorRegistroControllerTest {

    @InjectMocks
    ProfesorRegistroController profesorRegistroController;

    @Mock
    UsuarioService usuarioService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveProfesor_withValidData_returnsRedirectLogin() {
        Profesor profesor = new Profesor();
        profesor.setRole(rol.ROLE_TEACHER);
        MultipartFile archivo = new MockMultipartFile("archivo", new byte[0]);
        MultipartFile video = new MockMultipartFile("video", new byte[0]);
        MultipartFile imagen = new MockMultipartFile("imagen", new byte[0]);

        doNothing().when(usuarioService).save(profesor);
        String result = profesorRegistroController.saveProfesor(profesor, archivo, video, imagen);

        assertEquals("redirect:/login", result);
    }

    @Test
    public void saveProfesor_withEmptyFile_setsCertificadoFalse() {
        Profesor profesor = new Profesor();
        profesor.setRole(rol.ROLE_TEACHER);
        MultipartFile archivo = new MockMultipartFile("archivo", new byte[0]);
        MultipartFile video = new MockMultipartFile("video", new byte[0]);
        MultipartFile imagen = new MockMultipartFile("imagen", new byte[0]);

        doNothing().when(usuarioService).save(profesor);
        profesorRegistroController.saveProfesor(profesor, archivo, video, imagen);

        assertFalse(profesor.getCertificado());
    }

    @Test
    public void saveProfesor_withNonEmptyFile_setsCertificadoTrue() {
        Profesor profesor = new Profesor();
        profesor.setRole(rol.ROLE_TEACHER);
        MultipartFile archivo = new MockMultipartFile("archivo", "content".getBytes());
        MultipartFile video = new MockMultipartFile("video", new byte[0]);
        MultipartFile imagen = new MockMultipartFile("imagen", new byte[0]);

        doNothing().when(usuarioService).save(profesor);
        profesorRegistroController.saveProfesor(profesor, archivo, video, imagen);

        assertTrue(profesor.getCertificado());
    }
}