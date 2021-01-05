import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoTipo;
import ucab.empresae.servicio.TipoServicio;
import javax.ws.rs.core.Response;


public class TipoServicio_Test {

    @Test
    //Prueba Unitaria de la lista de tipos
    public void getTiposTest(){
        TipoServicio servicio = new TipoServicio();
        Response resultado = servicio.geTipos();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }

    @Test
    //Prueba Unitaria del consultar tipos
    public void getTipoTest(){
        TipoServicio servicio = new TipoServicio();
        Response resultado = servicio.getTipo(9);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

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

    @Test
    //Prueba Unitaria eliminacion de tipos
    public void deleteTipoTest(){
        TipoServicio servicio = new TipoServicio();
        Response resultado = servicio.deleteTipo(9);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }
}
