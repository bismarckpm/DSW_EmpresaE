package ucab.empresae.entidades;

import javax.json.bind.annotation.JsonbDateFormat;
import java.util.Date;

public class EncuestadoAux extends BaseEntity{


    public EncuestadoAux(){}

    public EncuestadoAux(long id){super(id);}

    private String estadoEstudioEncuestado;
    public String getEstadoEstudioEncuestado() {
        return estadoEstudioEncuestado;
    }
    public void setEstadoEstudioEncuestado(String estadoEstudioEncuestado) {
        this.estadoEstudioEncuestado = estadoEstudioEncuestado;
    }

    private String telefono;
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    private String estado;
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private String primerNombre;
    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }


    private String segundoNombre;
    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }


    private String primerApellido;
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    private String segundoApellido;
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    @JsonbDateFormat(value = "yyyy-MM-dd")
    private Date fechaNacimiento;
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    private EstadoCivilEntity estadoCivil;
    public EstadoCivilEntity getEstadoCivil() {
        return estadoCivil;
    }

    public void setEdocivil(EstadoCivilEntity estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    private NivelAcademicoEntity nivelacademico;
    public NivelAcademicoEntity getNivelacademico() {
        return nivelacademico;
    }

    public void setNivelacademico(NivelAcademicoEntity nivelacademico) {
        this.nivelacademico = nivelacademico;
    }


    private MedioConexionEntity medioconexion;
    public MedioConexionEntity getMedioconexion() {
        return medioconexion;
    }

    public void setMedioconexion(MedioConexionEntity medioconexion) {
        this.medioconexion = medioconexion;
    }

    private GeneroEntity genero;
    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }

    private OcupacionEntity ocupacion;
    public OcupacionEntity getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(OcupacionEntity ocupacion) {
        this.ocupacion = ocupacion;
    }

    private NivelSocioeconomicoEntity nivelsocioeco;
    public NivelSocioeconomicoEntity getNivelsocioeco() {
        return nivelsocioeco;
    }

    public void setNivelsocioeco(NivelSocioeconomicoEntity nivelsocioeco) {
        this.nivelsocioeco = nivelsocioeco;
    }

    private LugarEntity lugar;
    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    private UsuarioEntity usuario;
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
