package ucab.empresae.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nivel_socioeconomico", schema = "mercadeoucab")
public class NivelSocioeconomicoEntity extends BaseEntity{
    private String estado;
    private String nombre;
    private String descripcion;
    //private List<EncuestadoEntity> encuestados;
    //private List<LugarEntity> lugares;
    //private List<EstudioEntity> estudios;

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

    /*@OneToMany(mappedBy = "nivelsocioeco")
    public List<EncuestadoEntity> getEncuestados() {
        return encuestados;
    }

    public void setEncuestados(List<EncuestadoEntity> encuestados) {
        this.encuestados = encuestados;
    }

    @OneToMany(mappedBy = "nivelsocioeco")
    public List<LugarEntity> getLugares() {
        return lugares;
    }

    public void setLugares(List<LugarEntity> lugares) {
        this.lugares = lugares;
    }

    @OneToMany(mappedBy = "nivelsocioeco")
    public List<EstudioEntity> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<EstudioEntity> estudios) {
        this.estudios = estudios;
    }*/


    public NivelSocioeconomicoEntity() { }

    public NivelSocioeconomicoEntity(long id) {
        super(id);
    }

}
