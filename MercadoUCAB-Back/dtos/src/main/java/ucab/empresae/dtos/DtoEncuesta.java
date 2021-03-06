package ucab.empresae.dtos;

import java.util.Date;
import java.util.List;

public class DtoEncuesta extends DtoBase {

    //Atributos
    private String estado;
    private String fechaInicio;
    private String fechaFin;

    //Relaciones
    private DtoEstudio estudio;
    private DtoPregunta pregunta;

    private List<DtoPregunta> preguntas;

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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public DtoEstudio getEstudio() {
        return estudio;
    }

    public void setEstudio(DtoEstudio estudio) {
        this.estudio = estudio;
    }

    public DtoPregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(DtoPregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<DtoPregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<DtoPregunta> preguntas) {
        this.preguntas = preguntas;
    }

}
