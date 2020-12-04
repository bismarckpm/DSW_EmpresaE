package ucab.empresae.dtos;

import java.sql.Date;

public class DtoHijo extends DtoBase {

    //Atributos
    private String estado;
    private Date fechaNacimiento;

    //Relaciones
    private DtoEncuestado dtoEncuestado;
    private DtoGenero dtoGenero;

    //Contructores
    public DtoHijo() {
        super();
    }

    public DtoHijo(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public DtoEncuestado getDtoEncuestado() {
        return dtoEncuestado;
    }

    public void setDtoEncuestado(DtoEncuestado dtoEncuestado) {
        this.dtoEncuestado = dtoEncuestado;
    }

    public DtoGenero getDtoGenero() {
        return dtoGenero;
    }

    public void setDtoGenero(DtoGenero dtoGenero) {
        this.dtoGenero = dtoGenero;
    }

}
