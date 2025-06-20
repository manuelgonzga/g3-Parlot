package es.Parlot.Language_Learning.modelo;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "clasetipo")
public class Clasetipo {
    @Id
    @Column(name = "tipo_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 33)
    private String nombre;

    @OneToMany(mappedBy = "tipo")
    private Set<Clase> clases = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "clasetipos")
    private Set<Profesor> profesors = new LinkedHashSet<>();

    public Set<Profesor> getProfesors() {
        return profesors;
    }

    public void setProfesors(Set<Profesor> profesors) {
        this.profesors = profesors;
    }

    public Set<Clase> getClases() {
        return clases;
    }

    public void setClases(Set<Clase> clases) {
        this.clases = clases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clasetipo clasetipo = (Clasetipo) o;
        return Objects.equals(id, clasetipo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Clasetipo(){}

    public Clasetipo(String nombre){
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}