import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.NivelAcademicoServicio;
import javax.ws.rs.core.Response;

public class NivelAcademicoServicio_Test {

    @Test
    //Prueba Unitaria de la lista de Niveles Academicos
    public void getNivelesAcademicosTest(){
        NivelAcademicoServicio servicio = new NivelAcademicoServicio();
        Response resultado = servicio.getNivelesAcademicos();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
