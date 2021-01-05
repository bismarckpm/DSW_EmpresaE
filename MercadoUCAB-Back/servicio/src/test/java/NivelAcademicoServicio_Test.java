import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.NivelAcademicoServicio;
import javax.ws.rs.core.Response;

/**
 * Pruebas unitarias utilizadas para testear el funcionaiento de NivelAcademicoServicio.
 * @see NivelAcademicoServicio Servicio a probar.
 */
public class NivelAcademicoServicio_Test {

    /**
     * Prueba unitaria que testea que se puedan listar los niveles academicos registrados en sistema.
     */
    @Test
    //Prueba Unitaria de la lista de Niveles Academicos
    public void getNivelesAcademicosTest(){
        NivelAcademicoServicio servicio = new NivelAcademicoServicio();
        Response resultado = servicio.getNivelesAcademicos();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
