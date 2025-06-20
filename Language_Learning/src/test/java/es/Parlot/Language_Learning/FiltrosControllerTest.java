package es.Parlot.Language_Learning;

import es.Parlot.Language_Learning.controladores.FiltrosController;
import es.Parlot.Language_Learning.modelo.Profesor;
import es.Parlot.Language_Learning.servicios.ProfesorService;
import es.Parlot.Language_Learning.servicios.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FiltrosControllerTest {

    @InjectMocks
    FiltrosController filtrosController;

    @Mock
    ProfesorService profesorService;

    @Mock
    UsuarioService usuarioService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getProfesores_DatosValidos_returnsProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        Profesor profesor = new Profesor();
        profesores.add(profesor);
        when(profesorService.buscarProfesores(null, null, null, null, null, null)).thenReturn(profesores);

        Model model = new BindingAwareModelMap();
        String res = filtrosController.getProfesores(model, null, null, null, null, null, null);

        assertEquals("EncuentraProfesor", res);
        assertEquals(profesores, model.getAttribute("profesores"));
    }

    @Test
    public void getProfesor_IdValido_returnsProfesor() {
        Integer id = 1;
        Profesor profesor = new Profesor();
        when(profesorService.getProfesorById(id)).thenReturn(profesor);
        when(profesorService.getVideoPathByProfesorId(id)).thenReturn("videoPath");
        when(usuarioService.getFotoPathByUsuarioId(id)).thenReturn("fotoPath");

        Model model = new BindingAwareModelMap();
        String res = filtrosController.getProfesor(id, model);

        assertEquals("PerfilProfesor", res);
        assertEquals(profesor, model.getAttribute("profesor"));
        assertEquals("videoPath", model.getAttribute("videoPath"));
        assertEquals("fotoPath", model.getAttribute("fotoPath"));
    }
}