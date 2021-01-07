import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.EstadoCivilServicio;
import javax.ws.rs.core.Response;

public class EstadoCivilServicio_Test {

    @Test
    //Prueba Unitaria de la lista de Estados Civiles
    public void getMarcasTest(){
        EstadoCivilServicio servicio = new EstadoCivilServicio();
        Response resultado = servicio.getEstadosCiviles();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
