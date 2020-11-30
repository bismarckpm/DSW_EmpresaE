package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_usuario", schema = "mercadeoucab")
public class TipoUsuarioEntity extends BaseEntity{
    private String descripcion;
    private String estado;
    private List<UsuarioEntity> usuarios;

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @OneToMany(mappedBy = "tipousuario")
    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    }
}
