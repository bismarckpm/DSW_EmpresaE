package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pregunta", schema = "mercadeoucab")
public class PreguntaEntity extends BaseEntity{
    private String estado;
    private String descripcion;
    private TipoPreguntaEntity tipo;
    private SubcategoriaEntity subcategoria;
    private List<EncuestaEntity> encuestas;
    private List<PreguntaOpcionEntity> preguntaOpcion;

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
    @JoinColumn(name = "id_tipo", referencedColumnName = "id", nullable = false)
    public TipoPreguntaEntity getTipo() {
        return tipo;
    }

    public void setTipo(TipoPreguntaEntity tipo) {
        this.tipo = tipo;
    }

    @ManyToOne
    @JoinColumn(name = "id_subcategoria", referencedColumnName = "id")
    public SubcategoriaEntity getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(SubcategoriaEntity subcategoria) {
        this.subcategoria = subcategoria;
    }

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
}
