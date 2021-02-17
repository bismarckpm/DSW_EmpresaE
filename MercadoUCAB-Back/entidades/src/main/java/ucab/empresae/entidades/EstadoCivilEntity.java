package ucab.empresae.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "estado_civil", schema = "mercadeoucab")
public class EstadoCivilEntity extends BaseEntity{
    private String estado;
    private String nombre;
    //private List<EncuestadoEntity> encuestados;

    public EstadoCivilEntity( long id )
    {
        super( id );
    }

    public EstadoCivilEntity( ) { }

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

    /*@OneToMany(mappedBy = "edocivil")
    public List<EncuestadoEntity> getEncuestados() {
        return encuestados;
    }

    public void setEncuestados(List<EncuestadoEntity> encuestados) {
        this.encuestados = encuestados;
    }*/
}
