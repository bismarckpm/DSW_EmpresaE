package ucab.empresae.dtos;

import java.sql.Date;

public class DtoEstudio extends DtoBase {

    //Atributos
    private String estado;
    private String nombre;
    private String comentarioAnalista;
    private Integer edadMinima;
    private Integer edadMaxima;
    private Date fechaInicio;
    private Date fechaFin;

    //Relaciones
    private DtoLugar dtoLugar;
    private DtoNivelSocioEconomico dtoNivelSocioEconomico;
    private DtoSubcategoria dtoSubcategoria;

    //Constructores
    public DtoEstudio() {
        super();
    }

    public DtoEstudio(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentarioAnalista() {
        return comentarioAnalista;
    }

    public void setComentarioAnalista(String comentarioAnalista) {
        this.comentarioAnalista = comentarioAnalista;
    }

    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public DtoLugar getDtoLugar() {
        return dtoLugar;
    }

    public void setDtoLugar(DtoLugar dtoLugar) {
        this.dtoLugar = dtoLugar;
    }

    public DtoNivelSocioEconomico getDtoNivelSocioEconomico() {
        return dtoNivelSocioEconomico;
    }

    public void setDtoNivelSocioEconomico(DtoNivelSocioEconomico dtoNivelSocioEconomico) {
        this.dtoNivelSocioEconomico = dtoNivelSocioEconomico;
    }

    public DtoSubcategoria getDtoSubcategoria() {
        return dtoSubcategoria;
    }

    public void setDtoSubcategoria(DtoSubcategoria dtoSubcategoria) {
        this.dtoSubcategoria = dtoSubcategoria;
    }

}
