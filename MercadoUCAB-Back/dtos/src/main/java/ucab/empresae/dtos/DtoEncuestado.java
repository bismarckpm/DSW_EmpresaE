package ucab.empresae.dtos;

import java.sql.Date;

public class DtoEncuestado extends DtoBase {

    //Atributos
    private String estado;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaNacimiento;

    //Relaciones
    private DtoEstadoCivil estadoCivil;
    private DtoNivelAcademico nivelAcademico;
    private DtoMedioConexion medioConexion;
    private DtoGenero genero;
    private DtoOcupacion ocupacion;
    private DtoNivelSocioEconomico nivelSocioEconomico;
    private DtoLugar lugar;
    private DtoUsuario usuario;
    private DtoTelefono telefono;


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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public DtoEstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(DtoEstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public DtoNivelAcademico getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(DtoNivelAcademico nivelacademico) {
        this.nivelAcademico = nivelacademico;
    }

    public DtoMedioConexion getMedioConexion() {
        return medioConexion;
    }

    public void setMedioConexion(DtoMedioConexion medioconexion) {
        this.medioConexion = medioconexion;
    }

    public DtoGenero getGenero() {
        return genero;
    }

    public void setGenero(DtoGenero genero) {
        this.genero = genero;
    }

    public DtoOcupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(DtoOcupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    public DtoNivelSocioEconomico getNivelSocioEconomico() {
        return nivelSocioEconomico;
    }

    public void setNivelSocioEconomico(DtoNivelSocioEconomico nivelSocioEconomico) {
        this.nivelSocioEconomico = nivelSocioEconomico;
    }

    public DtoLugar getLugar() {
        return lugar;
    }

    public void setLugar(DtoLugar lugar) {
        this.lugar = lugar;
    }

    public DtoUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(DtoUsuario usuario) {
        this.usuario = usuario;
    }

    public DtoTelefono getTelefono() {
        return telefono;
    }

    public void setTelefono(DtoTelefono telefono) {
        this.telefono = telefono;
    }

}
