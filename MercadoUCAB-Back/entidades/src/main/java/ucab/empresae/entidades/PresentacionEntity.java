package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "presentacion", schema = "mercadeoucab")
public class PresentacionEntity extends BaseEntity{
    private String estado;
    private String descripcion;
    //private List<TipoEntity> tipos;

    public PresentacionEntity( )
    {

    }

    public PresentacionEntity( long id )
    {
        super( id );
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
/*
    @ManyToMany(mappedBy = "presentaciones")
    public List<TipoEntity> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoEntity> tipos) {
        this.tipos = tipos;
    }
 */
}
