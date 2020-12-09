package ucab.empresae.entidades;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "encuestado", schema = "mercadeoucab")
public class EncuestadoEntity extends BaseEntity{
    private String estado;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    private List<HijoEntity> hijos;
    private EstadoCivilEntity edocivil;
    private NivelAcademicoEntity nivelacademico;
    private MedioConexionEntity medioconexion;
    private GeneroEntity genero;
    private OcupacionEntity ocupacion;
    private NivelSocioeconomicoEntity nivelsocioeco;
    private LugarEntity lugar;
    private List<TelefonoEntity> telefonos;
    private UsuarioEntity usuario;
    private List<EstudioEncuestadoEntity> estudioencuestados;
    private List<RespuestaEntity> respuestas;

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "primer_nombre")
    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    @Basic
    @Column(name = "segundo_nombre")
    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    @Basic
    @Column(name = "primer_apellido")
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    @Basic
    @Column(name = "segundo_apellido")
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    @Basic
    @Column(name = "fecha_nacimiento")
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    @OneToMany(mappedBy = "encuestado")
    public List<HijoEntity> getHijos() {
        return hijos;
    }

    public void setHijos(List<HijoEntity> hijos) {
        this.hijos = hijos;
    }

    @ManyToOne
    @JoinColumn(name = "id_civil", referencedColumnName = "id", nullable = false)
    public EstadoCivilEntity getEdocivil() {
        return edocivil;
    }

    public void setEdocivil(EstadoCivilEntity edocivil) {
        this.edocivil = edocivil;
    }

    @ManyToOne
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id", nullable = false)
    public NivelAcademicoEntity getNivelacademico() {
        return nivelacademico;
    }

    public void setNivelacademico(NivelAcademicoEntity nivelacademico) {
        this.nivelacademico = nivelacademico;
    }

    @ManyToOne
    @JoinColumn(name = "id_conexion", referencedColumnName = "id", nullable = false)
    public MedioConexionEntity getMedioconexion() {
        return medioconexion;
    }

    public void setMedioconexion(MedioConexionEntity medioconexion) {
        this.medioconexion = medioconexion;
    }

    @ManyToOne
    @JoinColumn(name = "id_genero", referencedColumnName = "id", nullable = false)
    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }

    @ManyToOne
    @JoinColumn(name = "id_ocupacion", referencedColumnName = "id", nullable = false)
    public OcupacionEntity getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(OcupacionEntity ocupacion) {
        this.ocupacion = ocupacion;
    }

    @ManyToOne
    @JoinColumn(name = "id_nivel_socioeco", referencedColumnName = "id", nullable = false)
    public NivelSocioeconomicoEntity getNivelsocioeco() {
        return nivelsocioeco;
    }

    public void setNivelsocioeco(NivelSocioeconomicoEntity nivelsocioeco) {
        this.nivelsocioeco = nivelsocioeco;
    }

    @ManyToOne
    @JoinColumn(name = "id_lugar", referencedColumnName = "id", nullable = false)
    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    @OneToMany(mappedBy = "encuestado")
    public List<TelefonoEntity> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoEntity> telefonos) {
        this.telefonos = telefonos;
    }

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
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
}
