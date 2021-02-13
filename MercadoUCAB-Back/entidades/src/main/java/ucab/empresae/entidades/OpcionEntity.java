package ucab.empresae.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "opcion", schema = "mercadeoucab")
public class OpcionEntity extends BaseEntity{
    private String estado;
    private String descripcion;
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

    public OpcionEntity(String descripcion, String estado){
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public OpcionEntity() {
    }

    /*
    @OneToMany(mappedBy = "opcion")
    public List<PreguntaOpcionEntity> getPreguntaOpcion() {
        return preguntaOpcion;
    }

    public void setPreguntaOpcion(List<PreguntaOpcionEntity> preguntaOpcion) {
        this.preguntaOpcion = preguntaOpcion;
    }
    */
}
