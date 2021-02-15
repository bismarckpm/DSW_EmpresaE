package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pregunta", schema = "mercadeoucab")
public class PreguntaEntity extends BaseEntity{
    private String estado;
    private String descripcion;

    public PreguntaEntity( long id )
    {
        super( id );
    }

    public PreguntaEntity( )
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
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @ManyToOne
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    private TipoPreguntaEntity tipo;
    public TipoPreguntaEntity getTipo() {
        return tipo;
    }

    public void setTipo(TipoPreguntaEntity tipo) {
        this.tipo = tipo;
    }

    @ManyToOne
    @JoinColumn(name = "id_subcategoria", referencedColumnName = "id")
    private SubcategoriaEntity subcategoria;
    public SubcategoriaEntity getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(SubcategoriaEntity subcategoria) {
        this.subcategoria = subcategoria;
    }


}
