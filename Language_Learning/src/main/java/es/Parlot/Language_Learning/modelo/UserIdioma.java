package es.Parlot.Language_Learning.modelo;
import jakarta.persistence.*;

@Entity
@Table(name = "user_idioma")
public class UserIdioma {
    @EmbeddedId
    private UserIdiomaId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @MapsId("idiomaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idioma_id", nullable = false)
    private Idioma idioma;

    public UserIdiomaId getId() {
        return id;
    }

    public void setId(UserIdiomaId id) {
        this.id = id;
    }

    public Usuario getUser() {
        return usuario;
    }

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

}