package ucab.empresae.entidades;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subcategoria", schema = "mercadeoucab")
public class SubcategoriaEntity extends BaseEntity{
    private String estado;
    private String nombre;

    //private List<EstudioEntity> estudios;
    //private List<PreguntaEntity> preguntas;

    public SubcategoriaEntity( long id )
    {
        super( id );
    }

    public SubcategoriaEntity( )
    {

    }

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


    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", nullable = false)
    private CategoriaEntity categoria;
    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }
/*
    @OneToMany(mappedBy = "subcategoria")
    public List<EstudioEntity> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<EstudioEntity> estudios) {
        this.estudios = estudios;
    }*/

    @ManyToMany
    @JsonbTransient
    @JoinTable(name = "subcategoria_marca", schema = "mercadeoucab", joinColumns = @JoinColumn(name = "id_subcategoria", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_marca", referencedColumnName = "id", nullable = false))
    private List<MarcaEntity> marcas;
    public List<MarcaEntity> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<MarcaEntity> marcas) {
        this.marcas = marcas;
    }

    /*@OneToMany(mappedBy = "subcategoria")
    public List<PreguntaEntity> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaEntity> preguntas) {
        this.preguntas = preguntas;
    }*/
}
