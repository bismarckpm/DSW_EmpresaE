package ucab.empresae.entidades;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "marca", schema = "mercadeoucab")
public class MarcaEntity extends BaseEntity{
    private String estado;
    private String nombre;


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

    @ManyToMany(mappedBy = "marcas")
    private List<SubcategoriaEntity> subcategorias;
    public List<SubcategoriaEntity> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<SubcategoriaEntity> subcategorias) {
        this.subcategorias = subcategorias;
    }

    @ManyToMany
    @JsonbTransient
    @JoinTable(name = "marca_tipo", schema = "mercadeoucab", joinColumns = @JoinColumn(name = "id_marca", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_tipo", referencedColumnName = "id", nullable = false))
    private List<TipoEntity> tipos;
    public List<TipoEntity> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoEntity> tipos) {
        this.tipos = tipos;
    }
}
