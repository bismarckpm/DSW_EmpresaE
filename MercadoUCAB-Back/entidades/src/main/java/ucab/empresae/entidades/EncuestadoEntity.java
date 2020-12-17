package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "encuestado", schema = "mercadeoucab")
@NamedQueries({
        @NamedQuery(name = "getEncuestadoByUsuario", query = "select e from EncuestadoEntity e where e.usuario = :usuario"),
        @NamedQuery(name = "getDataMuestraEstudio", query = "select enc from EncuestadoEntity enc where enc.lugar = :lugar and enc.nivelsocioeco = :nivelSocioeconomico")
})
public class EncuestadoEntity extends BaseEntity{

    //private List<HijoEntity> hijos;
    //private List<TelefonoEntity> telefonos;
    //private List<EstudioEncuestadoEntity> estudioencuestados;
    // List<RespuestaEntity> respuestas;

    public EncuestadoEntity(){}

    public EncuestadoEntity(long id){super(id);}

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
    @Column(name = "primer_nombre")
    private String primerNombre;
    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    @Basic
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    @Basic
    @Column(name = "primer_apellido")
    private String primerApellido;
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    @Basic
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    @Basic
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    @ManyToOne
    @JoinColumn(name = "id_civil", referencedColumnName = "id", nullable = false)
    private EstadoCivilEntity estadoCivil;
    public EstadoCivilEntity getEstadoCivil() {
        return estadoCivil;
    }

    public void setEdocivil(EstadoCivilEntity estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @ManyToOne
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id", nullable = false)
    private NivelAcademicoEntity nivelacademico;
    public NivelAcademicoEntity getNivelacademico() {
        return nivelacademico;
    }

    public void setNivelacademico(NivelAcademicoEntity nivelacademico) {
        this.nivelacademico = nivelacademico;
    }

    @ManyToOne
    @JoinColumn(name = "id_conexion", referencedColumnName = "id", nullable = false)
    private MedioConexionEntity medioconexion;
    public MedioConexionEntity getMedioconexion() {
        return medioconexion;
    }

    public void setMedioconexion(MedioConexionEntity medioconexion) {
        this.medioconexion = medioconexion;
    }

    @ManyToOne
    @JoinColumn(name = "id_genero", referencedColumnName = "id", nullable = false)
    private GeneroEntity genero;
    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }

    @ManyToOne
    @JoinColumn(name = "id_ocupacion", referencedColumnName = "id", nullable = false)
    private OcupacionEntity ocupacion;
    public OcupacionEntity getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(OcupacionEntity ocupacion) {
        this.ocupacion = ocupacion;
    }

    @ManyToOne
    @JoinColumn(name = "id_nivel_socioeco", referencedColumnName = "id", nullable = false)
    private NivelSocioeconomicoEntity nivelsocioeco;
    public NivelSocioeconomicoEntity getNivelsocioeco() {
        return nivelsocioeco;
    }

    public void setNivelsocioeco(NivelSocioeconomicoEntity nivelsocioeco) {
        this.nivelsocioeco = nivelsocioeco;
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

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuario;
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }


    /*
    @OneToMany(mappedBy = "encuestado")
    public List<HijoEntity> getHijos() {
        return hijos;
    }

    public void setHijos(List<HijoEntity> hijos) {
        this.hijos = hijos;
    }

    @OneToMany(mappedBy = "encuestado")
    public List<TelefonoEntity> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoEntity> telefonos) {
        this.telefonos = telefonos;
    }


    @OneToMany(mappedBy = "encuestado")
    public List<EstudioEncuestadoEntity> getEstudioencuestados() {
        return estudioencuestados;
    }

    public void setEstudioencuestados(List<EstudioEncuestadoEntity> estudioencuestados) {
        this.estudioencuestados = estudioencuestados;
    }

    @OneToMany(mappedBy = "encuestado")
    public List<RespuestaEntity> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaEntity> respuestas) {
        this.respuestas = respuestas;
    }
     */
}
