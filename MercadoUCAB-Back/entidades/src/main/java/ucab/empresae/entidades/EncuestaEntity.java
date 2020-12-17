package ucab.empresae.entidades;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "encuesta", schema = "mercadeoucab")
@NamedQueries({
        @NamedQuery(name = "getEncuestas", query = "SELECT en from EncuestaEntity en where en.pregunta._id = (select MAX(en2.pregunta._id) from EncuestaEntity en2 where en2.estudio._id = en.estudio._id)"),
        @NamedQuery(name = "getPreguntasEncuesta", query = "select en from EncuestaEntity en where en.estudio._id = :id")
})
public class EncuestaEntity extends BaseEntity{
    private String estado;


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
    private Date fechaInicio;
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "fecha_fin")
    private Date fechaFin;
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @ManyToOne
    @JoinColumn(name = "id_estudio", referencedColumnName = "id", nullable = false)
    private EstudioEntity estudio;
    public EstudioEntity getEstudio() {
        return estudio;
    }

    public void setEstudio(EstudioEntity estudio) {
        this.estudio = estudio;
    }

    @ManyToOne
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id", nullable = false)
    private PreguntaEntity pregunta;
    public PreguntaEntity getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaEntity pregunta) {
        this.pregunta = pregunta;
    }
}
