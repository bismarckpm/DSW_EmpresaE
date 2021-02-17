package ucab.empresae.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_pregunta", schema = "mercadeoucab")
public class TipoPreguntaEntity extends BaseEntity{
    private String estado;
    private String tipo;
    //private List<PreguntaEntity> preguntas;

    public TipoPreguntaEntity( long id )
    {
        super( id );
    }

    public TipoPreguntaEntity( )
    {

    }

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /*
    @OneToMany(mappedBy = "tipo")
    public List<PreguntaEntity> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaEntity> preguntas) {
        this.preguntas = preguntas;
    }
     */
}
