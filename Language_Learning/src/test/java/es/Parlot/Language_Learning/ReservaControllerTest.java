package es.Parlot.Language_Learning;

import es.Parlot.Language_Learning.controladores.ReservaController;
import es.Parlot.Language_Learning.modelo.*;
import es.Parlot.Language_Learning.servicios.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservaControllerTest {

    @InjectMocks
    ReservaController reservaController;

    @Mock
    ClaseService claseService;

    @Mock
    IdiomaService idiomaService;

    @Mock
    AlumnoService alumnoService;

    @Mock
    ProfesorService profesorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void horario_withValidData_returnsOk() {
        ReservaController.TimeSlot timeSlot = new ReservaController.TimeSlot();
        timeSlot.start = "2022-01-01T00:00:00Z";
        timeSlot.end = "2022-01-01T01:00:00Z";
        Principal principal = mock(Principal.class);
        Clase clase = new Clase();
        Integer profesorId = 1;

        ResponseEntity<ReservaController.TimeSlot> result = reservaController.Horario(timeSlot, principal, profesorId, clase, null);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void getFranjasHorariasReservadas_withValidData_returnsTimeSlots() {
        Integer profesorId = 1;
        Set<Clase> clases = new HashSet<>();
        Clase clase = new Clase();
        clase.setFechaInicioUTC(Instant.parse("2022-01-01T00:00:00Z"));
        clase.setFechaFinUTC(Instant.parse("2022-01-01T01:00:00Z"));
        clases.add(clase);
        Profesor profesor = new Profesor();
        profesor.setClases(clases);

        when(profesorService.getProfesorById(profesorId)).thenReturn(profesor);

        List<ReservaController.TimeSlot> result = reservaController.getFranjasHorariasReservadas(profesorId);

        assertEquals(1, result.size());
        assertEquals("2022-01-01T00:00:00Z", result.get(0).start);
        assertEquals("2022-01-01T01:00:00Z", result.get(0).end);
    }
}