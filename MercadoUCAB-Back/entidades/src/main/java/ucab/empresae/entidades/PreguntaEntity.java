package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pregunta", schema = "mercadeoucab")
@NamedQueries({
        @NamedQuery(name = "getPreguntasbySubcategoria", query = "select p from PreguntaEntity p WHERE p.subcategoria._id = :id")
})
public class PreguntaEntity extends BaseEntity{
    private String estado;
    private String descripcion;
    //private List<EncuestaEntity> encuestas;
    //private List<PreguntaOpcionEntity> preguntaOpcion;

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

    /*
    @OneToMany(mappedBy = "pregunta")
    public List<EncuestaEntity> getEncuestas() {
        return encuestas;
    }

    public void setEncuestas(List<EncuestaEntity> encuestas) {
        this.encuestas = encuestas;
    }

    @OneToMany(mappedBy = "pregunta")
    public List<PreguntaOpcionEntity> getPreguntaOpcion() {
        return preguntaOpcion;
    }

    public void setPreguntaOpcion(List<PreguntaOpcionEntity> preguntaOpcion) {
        this.preguntaOpcion = preguntaOpcion;
    }
    */

}
