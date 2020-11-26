package ucab.empresae.entidades;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "encuesta", schema = "mercadeoucab")
public class EncuestaEntity extends BaseEntity{
    private String estado;
    private Date fechaInicio;
    private Date fechaFin;
    private EstudioEntity estudio;
    private PreguntaEntity pregunta;

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "fecha_inicio")
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "fecha_fin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @ManyToOne
    @JoinColumn(name = "id_estudio", referencedColumnName = "id", nullable = false)
    public EstudioEntity getEstudio() {
        return estudio;
    }

    public void setEstudio(EstudioEntity estudio) {
        this.estudio = estudio;
    }

    @ManyToOne
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id", nullable = false)
    public PreguntaEntity getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaEntity pregunta) {
        this.pregunta = pregunta;
    }
}
