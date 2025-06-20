package es.Parlot.Language_Learning.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "clase")
public class Clase {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private Clasetipo tipo;

    public Clase(){}

    public Clase(Profesor profesor, Alumno alumno, Clasetipo tipo, Idioma idioma, Instant fechaInicio, Instant fechaFin) {
        this.profesor = profesor;
        this.student = alumno;
        this.tipo = tipo;
        this.fechaInicioUTC = fechaInicio;
        this.fechaFinUTC = fechaFin;
        this.idioma = idioma;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clase_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Alumno student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idioma_id", nullable = false)
    private Idioma idioma;

    private String idiomaNombre;

    @ColumnDefault("0")
    @Column(name = "disponible", nullable = false)
    private Boolean disponible = false;

    @Column(name = "fecha_inicio", nullable = false)
    private Instant fechaInicioUTC;

    @Column(name = "fecha_fin")
    private Instant fechaFinUTC;

    public Clasetipo getTipo() {
        return tipo;
    }

    public void setTipo(Clasetipo tipo) {
        this.tipo = tipo;
    }


    public Instant getFechaFinUTC() {
        return fechaFinUTC;
    }

    public String getIdioma_nombre() {
        return idiomaNombre;
    }

    public void setIdioma_nombre(String idioma_nombre) {
        this.idiomaNombre = idioma_nombre;
    }

    public void setFechaFinUTC(Instant fechaFin) {
        this.fechaFinUTC = fechaFin;
    }

    public Instant getFechaInicioUTC() {
        return fechaInicioUTC;
    }

    public void setFechaInicioUTC(Instant fechaInicio) {
        this.fechaInicioUTC = fechaInicio;
    }

    public Instant getFechaInicioMadrid(){
        return fechaInicioUTC.plusSeconds(7200);
    }

    public Instant getFechaFinMadrid(){
        return fechaFinUTC.plusSeconds(7200);
    }
    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Alumno getStudent() {
        return student;
    }

    public void setStudent(Alumno student) {
        this.student = student;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clase clase = (Clase) o;
        return Objects.equals(id, clase.id) && Objects.equals(tipo, clase.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }


}