import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoPresentacion;
import ucab.empresae.servicio.PresentacionServicio;

import javax.ws.rs.core.Response;

/**
 * Pruebas unitarias encargadas de probar el servicio PresentacionServicio
 * @see PresentacionServicio Servicio a probar.
 */
public class PresentacionServicio_Test {

    /**
     * Prueba unitaria encargada de probar el método encargado de listar todas las presentaciones disponibles en la
     * base de datos.
     * @throws Exception En caso de encontrar alguna restricción a nivel de la base de datos.
     */
    @Test
    public void getPresentacionTest() throws Exception{
        PresentacionServicio servicio = new PresentacionServicio();
        Response resultado = servicio.getPresentaciones();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria que testea el funcionamiento del método encargado de añadir una presentación nueva al sistema.
     * @throws Exception En caso de encontrar alguna restricción a nivel de la bae de datos.
     */
    @Test
    public void addPresentacionTest() throws Exception{
        PresentacionServicio servicio = new PresentacionServicio();
        DtoPresentacion dtoPresentacion = new DtoPresentacion();
        dtoPresentacion.setDescripcion("Espuma");
        dtoPresentacion.setEstado("a");
        Response resultado = servicio.addPresentacion(dtoPresentacion);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria que testea el funcionamiento del método encargado de actualizar los datos de una presentación
     * especificada por medio de su id.
     * @throws Exception
     */
    @Test
    public void updatePresentacionTest() throws Exception{
        PresentacionServicio servicio = new PresentacionServicio();
        DtoPresentacion dtoPresentacion = new DtoPresentacion();
        dtoPresentacion.setDescripcion("EspumaUpdate");
        dtoPresentacion.setEstado("a");
        Response resultado = servicio.updatePresentacion(3, dtoPresentacion);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria que testea el funcionamiento del método encargado de borrar una presentación especificada por
     * medio de su id.
     * @throws Exception
     */
    @Test
    public void deletePresentacionTest() throws Exception{
        PresentacionServicio servicio = new PresentacionServicio();
        Response resultado = servicio.deletePresentacion(3);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }
}
