package ufro.dci.litlepaws.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mas_id")
    private Long id;

    @Column(name = "mas_nombre")
    private String nombre;

    @Column(name = "mas_descripcion",length = 10485760)
    private String descripcion;

    @Column(name = "mas_tipo")
    private String tipo;

    @Column(name = "mas_edad")
    private String edad;

    @Column(name="mas_meses")
    private int meses;

    @Column(name="mas_anos")
    private int anos;

    @Column(name = "mas_Chip")
    private boolean tieneChip;

    @Column(name = "mas_numeroChip")
    private String numeroChip;

    @Column(name = "mas_sexo")
    private String sexo;

    @Column(name = "mas_raza")
    private String raza;

    @Column(name = "mas_estirilizado")
    private boolean estirilizado;

    @Column(name = "mas_tipoHogar")
    private String tipoHogarSugerido;

    @Column(name= "mas_imagen")
    private byte[] imagen;

    @Column(name="mas_urgente")
    private boolean urgente;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="usu_id")
    private Usuario encargado;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean isTieneChip() {
        return tieneChip;
    }

    public void setTieneChip(boolean tieneChip) {
        this.tieneChip = tieneChip;
    }

    public String getNumeroChip() {
        return numeroChip;
    }

    public void setNumeroChip(String numeroChip) {
        this.numeroChip = numeroChip;
    }


    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public boolean isEstirilizado() {
        return estirilizado;
    }

    public void setEstirilizado(boolean estirilizado) {
        this.estirilizado = estirilizado;
    }

    public String getTipoHogarSugerido() {
        return tipoHogarSugerido;
    }

    public void setTipoHogarSugerido(String tipoHogarSugerido) {
        this.tipoHogarSugerido = tipoHogarSugerido;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Usuario getEncargado() {
        return encargado;
    }

    public void setEncargado(Usuario encargado) {
        this.encargado = encargado;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public int getAnos() {
        return anos;
    }

    public void setAnos(int anos) {
        this.anos = anos;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(int meses,int anos) {
        String año = " Años";
        String mes = " Meses";
        if(anos == 1){
            año = " Año";
        }
        if(meses == 1){
            mes = " Mes";
        }
        if(anos == 0 && meses==0){
              this.edad = "No se tiene registro";
        }else if(meses ==0){
            this.edad = anos+año;
        }else if(anos == 0) {
            this.edad = meses + mes;
        }else{
            this.edad = anos + año+ " y " + meses+ mes;
        }
    }
    public void cacularEdad() {
        String año = " Años";
        String mes = " Meses";
        if(this.anos == 1){
            año = " Año";
        }
        if(this.meses == 1){
            mes = " Mes";
        }
        if(this.anos == 0 && this.meses==0){
            this.edad = "No se tiene registro";
        }else if(this.meses ==0){
            this.edad = this.anos+año;
        }else if(this.anos == 0) {
            this.edad = this.meses + mes;
        }else{
            this.edad = this.anos + año+ " y " + this.meses+ mes;
        }
    }
}

