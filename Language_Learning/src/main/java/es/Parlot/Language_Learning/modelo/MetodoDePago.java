package es.Parlot.Language_Learning.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "metodo_de_pago")
public class MetodoDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usuario user;

    @Column(name = "tarjeta", nullable = false, length = 50)
    private String tarjeta;

    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;

    @Column(name = "titular", nullable = false, length = 100)
    private String titular;

    @Column(name = "año_caducidad", nullable = false)
    private Integer añoCaducidad;

    @Column(name = "mes_caducidad", nullable = false)
    private Integer mesCaducidad;

    public Integer getMesCaducidad() {
        return mesCaducidad;
    }

    public void setMesCaducidad(Integer mesCaducidad) {
        this.mesCaducidad = mesCaducidad;
    }

    public Integer getAñoCaducidad() {
        return añoCaducidad;
    }

    public void setAñoCaducidad(Integer añoCaducidad) {
        this.añoCaducidad = añoCaducidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getTarjetaOculta(){
        return "**** **** **** " + tarjeta.substring(tarjeta.length()-4);
    }

}