package ucab.empresae.entidades;

import javax.persistence.*;

@Entity
@Table(name = "respuesta", schema = "mercadeoucab")
@NamedQueries({
        @NamedQuery(name = "getCantidadRespuestas", query = "select count(re) from RespuestaEntity re where re.estudio._id = :id_estudio and re.preguntaOpcion._id in (select preopc._id from PreguntaOpcionEntity preopc where preopc.opcion = :opcion)")
})
public class RespuestaEntity extends BaseEntity{
    private String estado;
    private String texto;

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "texto")
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @ManyToOne
    @JoinColumn(name = "id_pregunta_opcion", referencedColumnName = "id", nullable = false)
    private PreguntaOpcionEntity preguntaOpcion;
    public PreguntaOpcionEntity getPreguntaOpcion() {
        return preguntaOpcion;
    }

    public void setPreguntaOpcion(PreguntaOpcionEntity preguntaOpcion) {
        this.preguntaOpcion = preguntaOpcion;
    }

    @ManyToOne
    @JoinColumn(name = "id_encuestado_pregunta", referencedColumnName = "id", nullable = false)
    private EncuestadoEntity encuestado;
    public EncuestadoEntity getEncuestado() {
        return encuestado;
    }

    public void setEncuestado(EncuestadoEntity encuestado) {
        this.encuestado = encuestado;
    }

    @ManyToOne
    @JoinColumn(name = "id_respuesta_estudio", referencedColumnName = "id", nullable = false)
    private EstudioEntity estudio;
    public EstudioEntity getEstudio() {
        return estudio;
    }

    public void setEstudio(EstudioEntity estudio) {
        this.estudio = estudio;
    }
}
