package ucab.empresae.entidades;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo", schema = "mercadeoucab")
public class TipoEntity extends BaseEntity{
    private String estado;
    private String nombre;
    private String descripcion;


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

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @ManyToMany(mappedBy = "tipos")
    private List<MarcaEntity> marcas;
    public List<MarcaEntity> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<MarcaEntity> marcas) {
        this.marcas = marcas;
    }

    @ManyToMany
    @JoinTable(name = "tipo_presentacion", schema = "mercadeoucab", joinColumns = @JoinColumn(name = "id_tipo", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_presentacion", referencedColumnName = "id", nullable = false))
    @JsonbTransient
    private List<PresentacionEntity> presentaciones;
    public List<PresentacionEntity> getPresentaciones() {
        return presentaciones;
    }

    public void setPresentaciones(List<PresentacionEntity> presentaciones) {
        this.presentaciones = presentaciones;
    }
}
