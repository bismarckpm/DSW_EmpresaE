package ucab.empresae.dtos;

public class DtoSubcategoria extends DtoBase {

    //Atributos
    private String estado;
    private String nombre;

    //Relaciones
    private DtoCategoria dtoCategoria;

    public String getEstado() {
        return estado;
    }

    //Constructores
    public DtoSubcategoria(long id) throws Exception {
        super(id);
    }

    public DtoSubcategoria() {
    }

    //Getters y Setters
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DtoCategoria getDtoCategoria() {
        return dtoCategoria;
    }

    public void setDtoCategoria(DtoCategoria dtoCategoria) {
        this.dtoCategoria = dtoCategoria;
    }

}
