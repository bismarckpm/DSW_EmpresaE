package ucab.empresae.dtos;

public class DtoGenero extends DtoBase {

    //Atributos
    private String dtoEstado;
    private String dtoNombre;

    //Constructores
    public DtoGenero() {
        super();
    }

    public DtoGenero(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return dtoEstado;
    }

    public void setEstado(String estado) {
        this.dtoEstado = estado;
    }

    public String getNombre() {
        return dtoNombre;
    }

    public void setNombre(String nombre) {
        this.dtoNombre = nombre;
    }

}
