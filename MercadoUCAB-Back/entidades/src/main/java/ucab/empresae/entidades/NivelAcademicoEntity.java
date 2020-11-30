package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nivel_academico", schema = "mercadeoucab")
public class NivelAcademicoEntity extends BaseEntity{
    private String estado;
    private String nombre;
    private List<EncuestadoEntity> encuestados;

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

    @OneToMany(mappedBy = "nivelacademico")
    public List<EncuestadoEntity> getEncuestados() {
        return encuestados;
    }

    public void setEncuestados(List<EncuestadoEntity> encuestados) {
        this.encuestados = encuestados;
    }
}
