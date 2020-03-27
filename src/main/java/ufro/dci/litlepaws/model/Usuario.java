package ufro.dci.litlepaws.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usu_id")
    private Long id;

    @Column
    private String authority = "user";

    @Column(name = "usu_nombre")
    private String nombre;

    @Column(name = "usu_rut")
    private String rut;

    @Column(name = "usu_telefono")
    private String telefono;

    @Column(name = "usu_email")
    private String email;

    @Column(name="usu_direccion")
    private String direccion;

    @Column(name = "usu_facebook")
    private String facebook;

    @Column(name = "usu_twitter")
    private String twitter;

    @Column(name = "usu_contraseña")
    private String contraseña;

    @Column(name="usu_veterinario")
    private boolean veterinario;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="usu_id")
    private Set<Mascota> Mascosta;


    public Usuario(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Set<Mascota> getMascosta() {
        return Mascosta;
    }

    public void setMascosta(Set<Mascota> mascosta) {
        this.Mascosta = mascosta;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public boolean isVeterinario() {
        return veterinario;
    }

    public void setVeterinario(boolean veterinario) {
        this.veterinario = veterinario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
