package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genero", schema = "mercadeoucab")
public class GeneroEntity extends BaseEntity {
    private String estado;
    private String nombre;
    private List<HijoEntity> hijos;
    private List<EncuestadoEntity> encuestados;
    private List<EstudioEntity> estudios;

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

    @OneToMany(mappedBy = "genero")
    public List<HijoEntity> getHijos() {
        return hijos;
    }

    public void setHijos(List<HijoEntity> hijos) {
        this.hijos = hijos;
    }

    @OneToMany(mappedBy = "genero")
    public List<EncuestadoEntity> getEncuestados() {
        return encuestados;
    }

    public void setEncuestados(List<EncuestadoEntity> encuestados) {
        this.encuestados = encuestados;
    }

    @ManyToMany(mappedBy = "generos")
    public List<EstudioEntity> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<EstudioEntity> estudios) {
        this.estudios = estudios;
    }
}
