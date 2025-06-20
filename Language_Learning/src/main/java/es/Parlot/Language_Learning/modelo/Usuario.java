package es.Parlot.Language_Learning.modelo;

import es.Parlot.Language_Learning.modelo.enums.rol;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "surname", length = 20)
    private String surname;

    @Column(name = "mail", nullable = false, length = 50)
    private String mail;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pais", nullable = false)
    private Pais pais;

    @Column(name = "prefijo", length = 10)
    private String prefijo;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_idioma",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "idioma_id"))
    private Set<Idioma> idiomas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<UserIdioma> userIdiomas = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    protected rol role;

    @Column(name = "foto_perfil_path")
    private String fotoPerfilPath;

    @OneToMany(mappedBy = "user")
    private Set<MetodoDePago> metodoDePagos = new LinkedHashSet<>();

    @Column(name = "saldo", nullable = false)
    private Double saldo = 0.0;

    private Double saldoToAdd;

    public Double getSaldoToAdd() {
        return saldoToAdd;
    }

    public void setSaldoToAdd(Double saldoToAdd) {
        this.saldoToAdd = saldoToAdd;
    }

    public Double getSaldo() {
        return saldo;
    }



    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Set<MetodoDePago> getMetodoDePagos() {
        return metodoDePagos;
    }

    public void setMetodoDePagos(Set<MetodoDePago> metodoDePagos) {
        this.metodoDePagos = metodoDePagos;
    }

    public Usuario(rol rol) {
    }

    public Usuario(){}

    public String getFotoPerfilPath() {
        return fotoPerfilPath;
    }

    public void setFotoPerfilPath(String fotoPerfilPath) {
        this.fotoPerfilPath = fotoPerfilPath;
    }

    public rol getRole() {
        return role;
    }

    public void setRole(rol role) {
        this.role = role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<UserIdioma> getUserIdiomas() {
        return userIdiomas;
    }

    public void setUserIdiomas(Set<UserIdioma> userIdiomas) {
        this.userIdiomas = userIdiomas;
    }

    public Set<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Set<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void addSaldo(Double saldo){
        this.saldo += saldo;
    }
}