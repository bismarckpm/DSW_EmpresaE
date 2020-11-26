package ucab.empresae.entidades;

import javax.persistence.*;

@Entity
@Table(name = "respuesta", schema = "mercadeoucab")
public class RespuestaEntity extends BaseEntity{
    private String estado;
    private String texto;
    private PreguntaOpcionEntity preguntaOpcion;
    private EncuestadoEntity encuestado;

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
    public PreguntaOpcionEntity getPreguntaOpcion() {
        return preguntaOpcion;
    }

    public void setPreguntaOpcion(PreguntaOpcionEntity preguntaOpcion) {
        this.preguntaOpcion = preguntaOpcion;
    }

    @ManyToOne
    @JoinColumn(name = "id_encuestado_pregunta", referencedColumnName = "id", nullable = false)
    public EncuestadoEntity getEncuestado() {
        return encuestado;
    }

    public void setEncuestado(EncuestadoEntity encuestado) {
        this.encuestado = encuestado;
    }
}
