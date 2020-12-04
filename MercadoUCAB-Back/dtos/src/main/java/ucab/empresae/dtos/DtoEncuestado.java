package ucab.empresae.dtos;

import java.sql.Date;

public class DtoEncuestado extends DtoBase {

    //Atributos
    private String estado;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;

    //Relaciones
    private DtoEstadoCivil dtoEstadoCivil;
    private DtoNivelAcademico dtoNivelAcademico;
    private DtoMedioConexion dtoMedioConexion;
    private DtoGenero dtoGenero;
    private DtoOcupacion dtoOcupacion;
    private DtoNivelSocioEconomico dtoNivelSocioeconomico;
    private DtoLugar dtoLugar;
    private DtoUsuario dtoUsuario;

    //Constructores
    public DtoEncuestado() {
        super();
    }

    public DtoEncuestado(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public DtoEstadoCivil getEestadoCivil() {
        return dtoEstadoCivil;
    }

    public void setEstadocivil(DtoEstadoCivil edocivil) {
        this.dtoEstadoCivil = edocivil;
    }

    public DtoNivelAcademico getNivelAcademico() {
        return dtoNivelAcademico;
    }

    public void setNivelAcademico(DtoNivelAcademico nivelacademico) {
        this.dtoNivelAcademico = nivelacademico;
    }

    public DtoMedioConexion getMedioConexion() {
        return dtoMedioConexion;
    }

    public void setMedioConexion(DtoMedioConexion medioconexion) {
        this.dtoMedioConexion = medioconexion;
    }

    public DtoGenero getGenero() {
        return dtoGenero;
    }

    public void setGenero(DtoGenero genero) {
        this.dtoGenero = genero;
    }

    public DtoOcupacion getOcupacion() {
        return dtoOcupacion;
    }

    public void setOcupacion(DtoOcupacion ocupacion) {
        this.dtoOcupacion = ocupacion;
    }

    public DtoNivelSocioEconomico getNivelSocioeco() {
        return dtoNivelSocioeconomico;
    }

    public void setNivelSocioeco(DtoNivelSocioEconomico nivelsocioeco) {
        this.dtoNivelSocioeconomico = nivelsocioeco;
    }

    public DtoLugar getLugar() {
        return dtoLugar;
    }

    public void setLugar(DtoLugar lugar) {
        this.dtoLugar = lugar;
    }

    public DtoUsuario getUsuario() {
        return dtoUsuario;
    }

    public void setUsuario(DtoUsuario usuario) {
        this.dtoUsuario = usuario;
    }

}
