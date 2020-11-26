package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "opcion", schema = "mercadeoucab")
public class OpcionEntity extends BaseEntity{
    private String estado;
    private String descripcion;
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

    @OneToMany(mappedBy = "opcion")
    public List<PreguntaOpcionEntity> getPreguntaOpcion() {
        return preguntaOpcion;
    }

    public void setPreguntaOpcion(List<PreguntaOpcionEntity> preguntaOpcion) {
        this.preguntaOpcion = preguntaOpcion;
    }
}
