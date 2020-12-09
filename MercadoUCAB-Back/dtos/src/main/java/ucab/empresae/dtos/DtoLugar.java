package ucab.empresae.dtos;

public class DtoLugar extends DtoBase {

    //Atributos
    private String estado;
    private String nombre;
    private String tipo;

    //Relaciones
    private DtoLugar dtoLugar;
    private DtoNivelSocioEconomico dtoNivelSocioEconomico;

    //Constructores
    public DtoLugar() {
        super();
    }

    public DtoLugar(long id) throws Exception {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public DtoLugar getDtoLugar() {
        return dtoLugar;
    }

    public void setDtoLugar(DtoLugar dtoLugar) {
        this.dtoLugar = dtoLugar;
    }

    public DtoNivelSocioEconomico getDtoNivelSocioEconomico() {
        return dtoNivelSocioEconomico;
    }

    public void setDtoNivelSocioEconomico(DtoNivelSocioEconomico dtoNivelSocioEconomico) {
        this.dtoNivelSocioEconomico = dtoNivelSocioEconomico;
    }

}
