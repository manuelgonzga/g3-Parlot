package es.Parlot.Language_Learning.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserIdiomaId implements Serializable {
    private static final long serialVersionUID = -8204654176503798291L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "idioma_id", nullable = false)
    private Integer idiomaId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getIdiomaId() {
        return idiomaId;
    }

    public void setIdiomaId(Integer idiomaId) {
        this.idiomaId = idiomaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserIdiomaId entity = (UserIdiomaId) o;
        return Objects.equals(this.idiomaId, entity.idiomaId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idiomaId, userId);
    }

}