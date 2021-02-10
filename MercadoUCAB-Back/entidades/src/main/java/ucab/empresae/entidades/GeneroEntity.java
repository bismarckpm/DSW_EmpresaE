package ucab.empresae.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "genero", schema = "mercadeoucab")
public class GeneroEntity extends BaseEntity {
    private String estado;
    private String nombre;
    //private List<HijoEntity> hijos;
    //private List<EncuestadoEntity> encuestados;

    public GeneroEntity( long id )
    {
        super( id );
    }

    public GeneroEntity( ) { }

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

    /*@OneToMany(mappedBy = "genero")
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
    }*/
}
