package es.Parlot.Language_Learning;

import es.Parlot.Language_Learning.controladores.AlumnoRegistroController;
import es.Parlot.Language_Learning.modelo.Alumno;
import es.Parlot.Language_Learning.modelo.Usuario;
import es.Parlot.Language_Learning.servicios.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;

public class AlumnoRegistroControllerTest {

    @InjectMocks
    AlumnoRegistroController alumnoRegistroController;

    @Mock
    UsuarioService usuarioService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveAlumno_withValidData_savesAlumno() {
        Alumno alumno = new Alumno();
        MultipartFile imagen = new MockMultipartFile("imagen", new byte[0]);

        doNothing().when(usuarioService).save(alumno);

        alumnoRegistroController.saveAlumno(alumno, imagen);

        verify(usuarioService, times(1)).save(alumno);
    }

    @Test
    public void editUser_withValidData_updatesUser() {
        Integer id = 1;
        Usuario updatedUser = new Usuario();
        updatedUser.setId(id);

        doNothing().when(usuarioService).update(updatedUser);

        Model model = mock(Model.class);
        alumnoRegistroController.editUser(id, updatedUser, model);

        verify(usuarioService, times(1)).update(updatedUser);
    }
}