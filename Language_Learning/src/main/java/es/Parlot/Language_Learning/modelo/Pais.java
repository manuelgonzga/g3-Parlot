package es.Parlot.Language_Learning.modelo;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pais")
public class Pais {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pais_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 30)
    private String nombre;

    @Column(name = "name", length = 30)
    private String name;

    @OneToMany(mappedBy = "pais")
    private Set<Usuario> users = new LinkedHashSet<>();

    public Set<Usuario> getUsers() {
        return users;
    }

    public void setUsers(Set<Usuario> users) {
        this.users = users;
    }

    @Column(name = "prefijo", nullable = false, length = 10)
    private String prefijo;

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getName() {
        return name;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}