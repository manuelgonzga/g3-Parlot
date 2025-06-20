package es.Parlot.Language_Learning.modelo;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "idioma")
public class Idioma {

    public Idioma(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idioma_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "idioma")
    private Set<Clase> clases = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idioma")
    private Set<UserIdioma> userIdiomas = new LinkedHashSet<>();

    public Set<UserIdioma> getUserIdiomas() {
        return userIdiomas;
    }

    public void setUserIdiomas(Set<UserIdioma> userIdiomas) {
        this.userIdiomas = userIdiomas;
    }

    public Set<Clase> getClases() {
        return clases;
    }

    public void setClases(Set<Clase> clases) {
        this.clases = clases;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idioma idioma = (Idioma) o;
        return Objects.equals(id, idioma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}