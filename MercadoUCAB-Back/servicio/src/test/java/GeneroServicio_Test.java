import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.GeneroServicio;
import javax.ws.rs.core.Response;

public class GeneroServicio_Test {

    @Test
    //Prueba Unitaria de la lista de Generos
    public void getGenerosTest(){
        GeneroServicio servicio = new GeneroServicio();
        Response resultado = servicio.getGeneros();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
