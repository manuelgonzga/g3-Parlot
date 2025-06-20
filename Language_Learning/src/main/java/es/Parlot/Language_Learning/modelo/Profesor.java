package es.Parlot.Language_Learning.modelo;

import es.Parlot.Language_Learning.modelo.enums.rol;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "profesor")
public class Profesor extends Usuario{

    public Profesor() {
        super(rol.ROLE_TEACHER);
    }

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    @Column(name = "certificado", nullable = false)
    private Boolean certificado = false;

    @Column(name = "price_per_hour", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerHour;

    @OneToMany(mappedBy = "profesor")
    private Set<Clase> clases = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "profesor_enseña_idioma",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "idioma_id"))
    private Set<Idioma> idiomasEnseña = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "profesor_tipoclase",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_clase_id"))
    private Set<Clasetipo> clasetipos = new LinkedHashSet<>();

    @Column(name = "certificados_y_estudios")
    private String certificadosYEstudios;

    @Column(name = "experiencia")
    private String experiencia;

    @Column(name = "tarifa_por_hora", precision = 38, scale = 2)
    private BigDecimal tarifaPorHora;

    @Column(name = "video_path")
    private String videoPath;

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public BigDecimal getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(BigDecimal tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getCertificadosYEstudios() {
        return certificadosYEstudios;
    }

    public void setCertificadosYEstudios(String certificadosYEstudios) {
        this.certificadosYEstudios = certificadosYEstudios;
    }

    public Set<Clasetipo> getClasetipos() {
        return clasetipos;
    }

    public void setClasetipos(Set<Clasetipo> clasetipos) {
        this.clasetipos = clasetipos;
    }

    public Set<Idioma> getIdiomasEnseña() {
        return idiomasEnseña;
    }

    public void setIdiomasEnseña(Set<Idioma> idiomas) {
        this.idiomasEnseña = idiomas;
    }

    public Set<Clase> getClases() {
        return clases;
    }

    public void setClases(Set<Clase> clases) {
        this.clases = clases;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Boolean getCertificado() {
        return certificado;
    }

    public void setCertificado(Boolean certificado) {
        this.certificado = certificado;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}