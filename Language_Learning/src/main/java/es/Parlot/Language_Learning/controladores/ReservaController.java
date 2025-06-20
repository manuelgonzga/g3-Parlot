package es.Parlot.Language_Learning.controladores;

import es.Parlot.Language_Learning.modelo.*;
import es.Parlot.Language_Learning.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

import java.time.*;
import java.util.*;


@Controller
public class ReservaController {

    @Autowired
    ClaseService claseService;

    @Autowired
    IdiomaService idiomaService;

    @Autowired
    AlumnoService alumnoService;
    @Autowired
    ProfesorService profesorService;
    @Autowired
    private UsuarioService usuarioService;

    public static class TimeSlot {
        public String start;
        public String end;
    }

    private Instant start, end;

    @ResponseBody
    @PostMapping("api/horario/{profesorId}")
    public ResponseEntity<TimeSlot> Horario(@RequestBody TimeSlot timeSlot, Principal principal, @PathVariable Integer profesorId, @ModelAttribute Clase clase,Model model) {
        ZonedDateTime zonedDateTimeStart = ZonedDateTime.parse(timeSlot.start);
        ZonedDateTime zonedDateTimeEnd = ZonedDateTime.parse(timeSlot.end);
        LocalDateTime localDateTimeStart = zonedDateTimeStart.toLocalDateTime();
        LocalDateTime localDateTimeEnd = zonedDateTimeEnd.toLocalDateTime();
        start = localDateTimeStart.toInstant(ZoneOffset.ofHours(2));
        end = localDateTimeEnd.toInstant(ZoneOffset.ofHours(2));
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @GetMapping("/api/horario/reservadas/{profesorId}")
    public List<TimeSlot> getFranjasHorariasReservadas(@PathVariable Integer profesorId) {

        List<TimeSlot> franjasHorariasReservadas = new ArrayList<>();
        for(Clase clase : profesorService.getProfesorById(profesorId).getClases()){
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.start = clase.getFechaInicioUTC().toString();
            timeSlot.end = clase.getFechaFinUTC().toString();
            franjasHorariasReservadas.add(timeSlot);
        }
        return franjasHorariasReservadas;
    }



    @GetMapping("/confirmar-clase/profesor/{profesorId}")
    public String registrarClase(@PathVariable("profesorId") Integer profesorId, Model model){
        model.addAttribute("profesor", profesorService.getProfesorById(profesorId));
        return "confirmarClase";
    }

    @ModelAttribute("clase")
    public Clase clase(){
        return new Clase();
    }

    @PostMapping("/save-clase/profesor/{profesorId}")
    public String saveClase(@ModelAttribute("clase") Clase clase, Principal principal, Model model, @PathVariable Integer profesorId){
        Alumno alumno = alumnoService.getAlumnoByUser(principal.getName());
        Profesor profesor = profesorService.getProfesorById(profesorId);
        Idioma idioma = clase.getIdioma();
        clase = new Clase(profesor, alumno, clase.getTipo(), clase.getIdioma(), start, end);
        clase.setIdioma(idioma);

        claseService.save(clase);

        return "redirect:/ClaseRegistroExitoso/" + clase.getId();
    }

    @ModelAttribute("MetodosPago")
    public Set<MetodoDePago> MetodosPago(Principal principal){
        Alumno alumno = alumnoService.getAlumnoByUser(principal.getName());
        return alumno.getMetodoDePagos();
    }

    @RequestMapping("/addPayMethodFromClase/{profesorId}")
    public String DesdePago(Model model, @PathVariable Integer profesorId, Principal principal){
        model.addAttribute("metodoPago", new MetodoDePago());
        model.addAttribute("usuario", profesorService.getProfesorById(profesorId));
        return "MetodoPagoRegistro";
    }

    @GetMapping("/ClaseRegistroExitoso/{id}")
    public String showClase(@PathVariable Integer id, Model model){
        Clase clase = claseService.getClaseById(id);
        model.addAttribute("clase", clase);
        return "ClaseRegistroExitoso";
    }

}