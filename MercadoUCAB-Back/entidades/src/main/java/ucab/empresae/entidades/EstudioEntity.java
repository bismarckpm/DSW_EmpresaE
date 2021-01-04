package ucab.empresae.entidades;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "estudio", schema = "mercadeoucab")
@NamedQueries({
        @NamedQuery(name = "getEstudiosCliente", query = "SELECT e FROM EstudioEntity e where e in (select c.estudio from ClienteEstudioEntity c where c.cliente.usuario._id = :id)"),
        @NamedQuery(name = "getEstudiosAnalista", query = "SELECT e FROM EstudioEntity e where e.analista._id = :id"),
        @NamedQuery(name = "getEstudios", query = "select es from EstudioEntity es where es.lugar = :lugar and es.nivelSocioEconomico = :nivelSocioeconomico"),
        @NamedQuery(name = "getEstudiosEncuestado", query = "select es from EstudioEntity es where es._id in (select estenc.estudio._id from EstudioEncuestadoEntity estenc where estenc.encuestado = :encuestado) and es.estado != 'Culminado'"),
        @NamedQuery(name = "getEstudiosSinEncuesta", query = "select es from EstudioEntity es where es._id not in (select enc.estudio._id from EncuestaEntity enc)")
})
public class EstudioEntity extends BaseEntity{
    //private List<ClienteEstudioEntity> clienteestudios;
    //private List<EstudioEncuestadoEntity> estudioencuestados;
    // List<EncuestaEntity> encuestas;

    //Constructores
    public EstudioEntity(long id) {
        super(id);
    }

    public EstudioEntity() {
    }

    @Basic
    @Column(name = "estado")
    private String estado;
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "nombre")
    private String nombre;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "comentario_analista")
    private String comentarioAnalista;
    public String getComentarioAnalista() {
        return comentarioAnalista;
    }

    public void setComentarioAnalista(String comentarioAnalista) {
        this.comentarioAnalista = comentarioAnalista;
    }

    @Basic
    @Column(name = "edad_minima")
    private Integer edadMinima;
    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    @Basic
    @Column(name = "edad_maxima")
    private Integer edadMaxima;
    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    @Basic
    @Column(name = "fecha_inicio")
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private Date fechaInicio;
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "fecha_fin")
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private Date fechaFin;
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @ManyToOne
    @JoinColumn(name = "id_lugar", referencedColumnName = "id", nullable = false)
    private LugarEntity lugar;
    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    @ManyToOne
    @JoinColumn(name = "id_nivelsocioeco", referencedColumnName = "id", nullable = false)
    private NivelSocioeconomicoEntity nivelSocioEconomico;
    public NivelSocioeconomicoEntity getNivelSocioEconomico() {
        return nivelSocioEconomico;
    }

    public void setNivelSocioEconomico(NivelSocioeconomicoEntity nivelsocioeco) {
        this.nivelSocioEconomico = nivelsocioeco;
    }

    @ManyToOne
    @JoinColumn(name = "id_subcat", referencedColumnName = "id", nullable = false)
    private SubcategoriaEntity subcategoria;
    public SubcategoriaEntity getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(SubcategoriaEntity subcategoria) {
        this.subcategoria = subcategoria;
    }


    @ManyToOne
    @JoinColumn(name = "id_usuario_analista", referencedColumnName = "id")
    private UsuarioEntity analista;
    public UsuarioEntity getAnalista() { return analista; }

    public void setAnalista(UsuarioEntity analista) { this.analista = analista; }

    @ManyToMany
    @JsonbTransient
    @JoinTable(name = "estudio_genero", schema = "mercadeoucab", joinColumns = @JoinColumn(name = "id_estudio", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_genero", referencedColumnName = "id", nullable = false))
    private List<GeneroEntity> generos;
    public List<GeneroEntity> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroEntity> generos) {
        this.generos = generos;
    }

    /*@OneToMany(mappedBy = "estudio")
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
    }*/

}
