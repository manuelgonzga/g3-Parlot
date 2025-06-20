package es.Parlot.Language_Learning.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProfesorEnseñaIdiomaId implements Serializable {
    private static final long serialVersionUID = 3049283355623223453L;
    @Column(name = "idioma_id", nullable = false)
    private Integer idiomaId;

    @Column(name = "profesor_id", nullable = false)
    private Integer profesorId;

    public Integer getIdiomaId() {
        return idiomaId;
    }

    public void setIdiomaId(Integer idiomaId) {
        this.idiomaId = idiomaId;
    }

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProfesorEnseñaIdiomaId entity = (ProfesorEnseñaIdiomaId) o;
        return Objects.equals(this.idiomaId, entity.idiomaId) &&
                Objects.equals(this.profesorId, entity.profesorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idiomaId, profesorId);
    }

}