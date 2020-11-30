package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categoria", schema = "mercadeoucab")
public class CategoriaEntity extends BaseEntity{
    private String estado;
    private String nombre;
    private List<SubcategoriaEntity> subcategorias;

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(mappedBy = "categoria")
    public List<SubcategoriaEntity> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<SubcategoriaEntity> subcategorias) {
        this.subcategorias = subcategorias;
    }
}
