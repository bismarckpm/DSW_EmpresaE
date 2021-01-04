package ucab.empresae.dtos;

import java.util.List;

public class DtoEstudio extends DtoBase {

    //Atributos
    private String estado;
    private String nombre;
    private String comentarioAnalista;
    private Integer edadMinima;
    private Integer edadMaxima;
    private String fechaInicio;
    private String fechaFin;

    //Relaciones
    private DtoLugar lugar;
    private DtoNivelSocioEconomico nivelSocioEconomico;
    private DtoSubcategoria subcategoria;
    private DtoUsuario analista;
    private List<DtoGenero> generos;

    //Constructores
    public DtoEstudio() {
        super();
    }

    public DtoEstudio(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentarioAnalista() {
        return comentarioAnalista;
    }

    public void setComentarioAnalista(String comentarioAnalista) {
        this.comentarioAnalista = comentarioAnalista;
    }

    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public DtoLugar getLugar() {
        return lugar;
    }

    public void setLugar(DtoLugar lugar) {
        this.lugar = lugar;
    }

    public DtoNivelSocioEconomico getNivelSocioEconomico() {
        return nivelSocioEconomico;
    }

    public void setNivelSocioEconomico(DtoNivelSocioEconomico nivelSocioEconomico) {
        this.nivelSocioEconomico = nivelSocioEconomico;
    }

    public DtoSubcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(DtoSubcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public DtoUsuario getAnalista() {
        return analista;
    }

    public void setAnalista(DtoUsuario analista) {
        this.analista = analista;
    }

    public List<DtoGenero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<DtoGenero> generos) {
        this.generos = generos;
    }
}
