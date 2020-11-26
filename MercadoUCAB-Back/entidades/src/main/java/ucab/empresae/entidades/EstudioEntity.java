package ucab.empresae.entidades;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "estudio", schema = "mercadeoucab")
public class EstudioEntity extends BaseEntity{
    private String estado;
    private String nombre;
    private String comentarioAnalista;
    private Integer edadMinima;
    private Integer edadMaxima;
    private Date fechaInicio;
    private Date fechaFin;
    private LugarEntity lugar;
    private NivelSocioeconomicoEntity nivelsocioeco;
    private SubcategoriaEntity subcategoria;
    private List<GeneroEntity> generos;
    private List<ClienteEstudioEntity> clienteestudios;
    private List<EstudioEncuestadoEntity> estudioencuestados;
    private List<EncuestaEntity> encuestas;

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
    @Column(name = "comentario_analista")
    public String getComentarioAnalista() {
        return comentarioAnalista;
    }

    public void setComentarioAnalista(String comentarioAnalista) {
        this.comentarioAnalista = comentarioAnalista;
    }

    @Basic
    @Column(name = "edad_minima")
    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    @Basic
    @Column(name = "edad_maxima")
    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    @Basic
    @Column(name = "fecha_inicio")
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "fecha_fin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @ManyToOne
    @JoinColumn(name = "id_lugar", referencedColumnName = "id", nullable = false)
    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    @ManyToOne
    @JoinColumn(name = "id_nivelsocioeco", referencedColumnName = "id", nullable = false)
    public NivelSocioeconomicoEntity getNivelsocioeco() {
        return nivelsocioeco;
    }

    public void setNivelsocioeco(NivelSocioeconomicoEntity nivelsocioeco) {
        this.nivelsocioeco = nivelsocioeco;
    }

    @ManyToOne
    @JoinColumn(name = "id_subcat", referencedColumnName = "id", nullable = false)
    public SubcategoriaEntity getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(SubcategoriaEntity subcategoria) {
        this.subcategoria = subcategoria;
    }

    @ManyToMany
    @JoinTable(name = "estudio_genero", schema = "mercadeoucab", joinColumns = @JoinColumn(name = "id_estudio", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_genero", referencedColumnName = "id", nullable = false))
    public List<GeneroEntity> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroEntity> generos) {
        this.generos = generos;
    }

    @OneToMany(mappedBy = "estudio")
    public List<ClienteEstudioEntity> getClienteestudios() {
        return clienteestudios;
    }

    public void setClienteestudios(List<ClienteEstudioEntity> clienteestudios) {
        this.clienteestudios = clienteestudios;
    }

    @OneToMany(mappedBy = "estudio")
    public List<EstudioEncuestadoEntity> getEstudioencuestados() {
        return estudioencuestados;
    }

    public void setEstudioencuestados(List<EstudioEncuestadoEntity> estudioencuestados) {
        this.estudioencuestados = estudioencuestados;
    }

    @OneToMany(mappedBy = "estudio")
    public List<EncuestaEntity> getEncuestas() {
        return encuestas;
    }

    public void setEncuestas(List<EncuestaEntity> encuestas) {
        this.encuestas = encuestas;
    }
}
