package ucab.empresae.dtos;

import java.sql.Date;

public class DtoEstudioEncuestado extends DtoBase {

    //Atributos
    private String estado;
    private Date fechaRealizacion;

    //Relaciones
    private DtoEncuestado dtoEncuestado;
    private DtoEstudio dtoEstudio;

    //Constructores
    public DtoEstudioEncuestado() {
        super();
    }

    public DtoEstudioEncuestado(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public DtoEncuestado getDtoEncuestado() {
        return dtoEncuestado;
    }

    public void setDtoEncuestado(DtoEncuestado dtoEncuestado) {
        this.dtoEncuestado = dtoEncuestado;
    }

    public DtoEstudio getDtoEstudio() {
        return dtoEstudio;
    }

    public void setDtoEstudio(DtoEstudio dtoEstudio) {
        this.dtoEstudio = dtoEstudio;
    }
}
