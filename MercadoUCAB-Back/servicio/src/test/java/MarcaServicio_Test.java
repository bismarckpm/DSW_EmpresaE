import org.junit.Assert;
import org.junit.Test;
import javax.ws.rs.core.Response;


public class MarcaServicio_Test {

    @Test
    public void getMarcasTest() throws Exception{
        ucab.empresae.servicio.MarcaServicio servicio = new ucab.empresae.servicio.MarcaServicio();
        Response resultado = servicio.getMarcas();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }

    @Test
    public void getMarcaTest() throws Exception{
        ucab.empresae.servicio.MarcaServicio servicio = new ucab.empresae.servicio.MarcaServicio();
        Response resultado = servicio.getMarca(1);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

}
