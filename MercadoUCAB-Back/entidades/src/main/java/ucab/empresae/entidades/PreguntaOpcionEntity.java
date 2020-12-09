package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pregunta_opcion", schema = "mercadeoucab")
public class PreguntaOpcionEntity extends BaseEntity{
    private String estado;
    private PreguntaEntity pregunta;
    private OpcionEntity opcion;
    private List<RespuestaEntity> respuestas;

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @ManyToOne
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id", nullable = false)
    public PreguntaEntity getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaEntity pregunta) {
        this.pregunta = pregunta;
    }

    @ManyToOne
    @JoinColumn(name = "id_opcion", referencedColumnName = "id", nullable = false)
    public OpcionEntity getOpcion() {
        return opcion;
    }

    public void setOpcion(OpcionEntity opcion) {
        this.opcion = opcion;
    }

    @OneToMany(mappedBy = "preguntaOpcion")
    public List<RespuestaEntity> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaEntity> respuestas) {
        this.respuestas = respuestas;
    }
}
