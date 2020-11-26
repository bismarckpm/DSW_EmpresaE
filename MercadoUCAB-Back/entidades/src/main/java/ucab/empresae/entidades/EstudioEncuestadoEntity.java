package ucab.empresae.entidades;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "estudio_encuestado", schema = "mercadeoucab")
public class EstudioEncuestadoEntity extends BaseEntity{
    private String estado;
    private Date fechaRealizacion;
    private EncuestadoEntity encuestado;
    private EstudioEntity estudio;

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "fecha_realizacion")
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    @ManyToOne
    @JoinColumn(name = "id_encuestado", referencedColumnName = "id", nullable = false)
    public EncuestadoEntity getEncuestado() {
        return encuestado;
    }

    public void setEncuestado(EncuestadoEntity encuestado) {
        this.encuestado = encuestado;
    }

    @ManyToOne
    @JoinColumn(name = "id_estudio", referencedColumnName = "id", nullable = false)
    public EstudioEntity getEstudio() {
        return estudio;
    }

    public void setEstudio(EstudioEntity estudio) {
        this.estudio = estudio;
    }
}
