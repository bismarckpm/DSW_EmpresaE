package ucab.empresae.dtos;

import java.sql.Date;

public class DtoEncuesta extends DtoBase {

    //Atributos
    private String estado;
    private Date fechaInicio;
    private Date fechaFin;

    //Relaciones
    private DtoEstudio dtoEstudio;
    private DtoPregunta dtoPregunta;

    //Constructores
    public DtoEncuesta() {
        super();
    }

    public DtoEncuesta(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public DtoEstudio getEstudio() {
        return dtoEstudio;
    }

    public void setEstudio(DtoEstudio estudio) {
        this.dtoEstudio = estudio;
    }

    public DtoPregunta getPregunta() {
        return dtoPregunta;
    }

    public void setPregunta(DtoPregunta pregunta) {
        this.dtoPregunta = pregunta;
    }

}
