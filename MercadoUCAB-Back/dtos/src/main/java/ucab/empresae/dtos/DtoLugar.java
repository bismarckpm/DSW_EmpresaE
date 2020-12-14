package ucab.empresae.dtos;

public class DtoLugar extends DtoBase {

    //Atributos
    private String estado;
    private String nombre;
    private String tipo;

    //Relaciones
    private DtoLugar lugar;
    private DtoNivelSocioEconomico nivelSocioEconomico;

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
}
