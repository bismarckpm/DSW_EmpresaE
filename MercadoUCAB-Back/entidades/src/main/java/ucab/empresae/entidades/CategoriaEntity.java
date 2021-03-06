package ucab.empresae.entidades;

import javax.persistence.*;

@Entity
@Table(name = "categoria", schema = "mercadeoucab")
public class CategoriaEntity extends BaseEntity{
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

    public CategoriaEntity(long id) {
        super(id);
    }

    public CategoriaEntity() {

    }
}
