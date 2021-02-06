import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoTipo;
import ucab.empresae.servicio.TipoServicio;
import javax.ws.rs.core.Response;

/**
 * Pruebas unitarias encargadas de testear el funcionamiento del servicio TipoServicio
 * @see TipoServicio Servicio a probar.
 */
public class TipoServicio_Test {

    /**
     * Prueba unitaria que testea el funcionamiento del método encargado de listar todos los tipos registrados
     * en el sistema.
     */
    @Test
    //Prueba Unitaria de la lista de tipos
    public void getTiposTest(){
        TipoServicio servicio = new TipoServicio();
        Response resultado = servicio.geTipos();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria que testea el funcionamiento del método encargado de retornar un tipo especificado por medio
     * de su id.
     */
    @Test
    //Prueba Unitaria del consultar tipos
    public void getTipoTest(){
        TipoServicio servicio = new TipoServicio();
        Response resultado = servicio.getTipo(9);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria que testea el funcionamiento del método encargado de añadir un tipó nuevo al sistema.
     */
    @Test
    //Prueba Unitaria del registro de tipos
    public void addTipoTest(){
        TipoServicio servicio = new TipoServicio();
        DtoTipo dtoTipo = new DtoTipo();
        dtoTipo.setNombre("prueba");
        dtoTipo.setDescripcion("prueba");
        dtoTipo.setEstado("A");
        Response resultado = servicio.addTipo(dtoTipo);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria que testea el funcionamiento del método encargado de actualizar la información de un tipo
     * especificado por medio de su id.
     */
    @Test
    //Prueba Unitaria modificacion de tipos
    public void updateTipoTest(){
        TipoServicio servicio = new TipoServicio();
        DtoTipo dtoTipo = new DtoTipo();
        dtoTipo.setNombre("pruebaUpdate");
        dtoTipo.setDescripcion("pruebaUpdate");
        dtoTipo.setEstado("A");
        Response resultado = servicio.updateTipo(9, dtoTipo);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria que testea el funcionamiento del método encargado de borrar un tipo especificado por medio de su
     * id.
     */
    @Test
    //Prueba Unitaria eliminacion de tipos
    public void deleteTipoTest(){
        TipoServicio servicio = new TipoServicio();
        Response resultado = servicio.deleteTipo(9);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }
}
