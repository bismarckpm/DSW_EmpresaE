package ucab.empresae.entidades;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class EstudioAux extends BaseEntity{

    //Constructores
    public EstudioAux(long id) {
        super(id);
    }

    public EstudioAux() {
    }

    private String estadoEstudioEncuestado;
    public String getEstadoEstudioEncuestado() {
        return estadoEstudioEncuestado;
    }
    public void setEstadoEstudioEncuestado(String estadoEstudioEncuestado) {
        this.estadoEstudioEncuestado = estadoEstudioEncuestado;
    }

    private String estado;
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


    private String nombre;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    private String comentarioAnalista;
    public String getComentarioAnalista() {
        return comentarioAnalista;
    }
    public void setComentarioAnalista(String comentarioAnalista) {
        this.comentarioAnalista = comentarioAnalista;
    }


    private Integer edadMinima;
    public Integer getEdadMinima() {
        return edadMinima;
    }
    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }


    private Integer edadMaxima;
    public Integer getEdadMaxima() {
        return edadMaxima;
    }
    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    @JsonbDateFormat(value = "yyyy-MM-dd")
    private Date fechaInicio;
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    @JsonbDateFormat(value = "yyyy-MM-dd")
    private Date fechaFin;
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }


    private LugarEntity lugar;
    public LugarEntity getLugar() {
        return lugar;
    }
    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }


    private NivelSocioeconomicoEntity nivelSocioEconomico;
    public NivelSocioeconomicoEntity getNivelSocioEconomico() {
        return nivelSocioEconomico;
    }
    public void setNivelSocioEconomico(NivelSocioeconomicoEntity nivelsocioeco) {
        this.nivelSocioEconomico = nivelsocioeco;
    }


    private SubcategoriaEntity subcategoria;
    public SubcategoriaEntity getSubcategoria() {
        return subcategoria;
    }
    public void setSubcategoria(SubcategoriaEntity subcategoria) {
        this.subcategoria = subcategoria;
    }


    private UsuarioEntity analista;
    public UsuarioEntity getAnalista() { return analista; }
    public void setAnalista(UsuarioEntity analista) { this.analista = analista; }

}
