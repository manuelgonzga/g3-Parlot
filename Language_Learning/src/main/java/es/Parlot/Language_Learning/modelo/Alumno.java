package es.Parlot.Language_Learning.modelo;

import es.Parlot.Language_Learning.modelo.enums.rol;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "alumno")
public class Alumno extends Usuario{
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "student")
    private Set<Clase> clases = new LinkedHashSet<>();

    public Alumno() {
        super(rol.ROLE_STUDENT);
    }

    public void setRol() {
        super.setRole(rol.ROLE_STUDENT);
    }

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    public Set<Clase> getClases() {
        return clases;
    }

    public void setClases(Set<Clase> clases) {
        this.clases = clases;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}