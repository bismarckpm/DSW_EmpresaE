import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.OcupacionServicio;
import javax.ws.rs.core.Response;

public class OcupacionServicio_Test {

    @Test
    //Prueba Unitaria de la lista de Ocupaciones
    public void getOcupacionesTest(){
        OcupacionServicio servicio = new OcupacionServicio();
        Response resultado = servicio.getOcupaciones();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
