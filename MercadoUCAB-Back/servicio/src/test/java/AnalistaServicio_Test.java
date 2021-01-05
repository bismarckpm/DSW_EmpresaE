import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.AnalistaServicio;
import javax.ws.rs.core.Response;

public class AnalistaServicio_Test {

    @Test
    //Prueba Unitaria de la lista de Analistas
    public void getAnalistasTest(){
        AnalistaServicio servicio = new AnalistaServicio();
        Response resultado = servicio.getAnalistas();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba Unitaria de la lista de Estudios de un Analista
    public void getAnalistaTest(){
        AnalistaServicio servicio = new AnalistaServicio();
        Response resultado = servicio.getAnalista(3);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

}
