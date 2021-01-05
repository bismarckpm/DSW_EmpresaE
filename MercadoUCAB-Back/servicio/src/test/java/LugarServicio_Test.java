import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.LugarServicio;
import javax.ws.rs.core.Response;

public class LugarServicio_Test {

    @Test
    //Prueba Unitaria de la lista de Lugares de Tipo Estado
    public void getLugaresTest(){
        LugarServicio servicio = new LugarServicio();
        Response resultado = servicio.getLugares();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }

    @Test
    //Prueba Unitaria consulta de un Lugar
    public void getLugarTest(){
        LugarServicio servicio = new LugarServicio();
        Response resultado = servicio.getLugar(323);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }
}
