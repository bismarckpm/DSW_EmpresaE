package ucab.empresae.dtos;

public class DtoSubcategoria extends DtoBase {

    //Atributos
    private String estado;
    private String nombre;

    //Relaciones
    private DtoCategoria categoria;

    public String getEstado() {
        return estado;
    }

    //Constructores
    public DtoSubcategoria(long id){
        super(id);
    }

    public DtoSubcategoria() {
        super();
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

    public DtoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(DtoCategoria Categoria) {
        this.categoria = Categoria;
    }

}
