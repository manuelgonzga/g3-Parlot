package es.Parlot.Language_Learning.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "`profesor_enseña_idioma`")
public class ProfesorEnseñaIdioma {
    @EmbeddedId
    private ProfesorEnseñaIdiomaId id;

    @MapsId("idiomaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idioma_id", nullable = false)
    private Idioma idioma;

    @MapsId("profesorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    public ProfesorEnseñaIdiomaId getId() {
        return id;
    }

    public void setId(ProfesorEnseñaIdiomaId id) {
        this.id = id;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

}