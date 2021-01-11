import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.dtos.DtoSubcategoria;
import ucab.empresae.dtos.DtoTipoPregunta;
import ucab.empresae.entidades.PreguntaAux;
import ucab.empresae.servicio.PreguntaServicio;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Pruebas unitarias que testean el funcionamiento del servicio PreguntaServicio
 * @see PreguntaServicio Servicio a probar.
 */
public class PreguntaServicio_Test {

    /**
     * Prueba unitaria utilizada para probar que se puedan listar todas las preguntas registradas en el sistema.
     * @throws Exception En caso de algun constraint a nivel de la base de datos.
     */
    @Test
    public void getPreguntasTest() throws Exception
    {
        PreguntaServicio servicio = new PreguntaServicio();
        Response respuesta = servicio.getPreguntas();
        Assert.assertEquals( respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria utilizada para probar que se pueda añadir una pregunta nueva al sistema.
     * @throws Exception En caso de encontrar alguna restricción a nivel de la base de datos.
     */
    @Test
    public void addPreguntaTest() throws Exception {
        PreguntaServicio servicio = new PreguntaServicio();
        DtoPregunta dtoPregunta = new DtoPregunta();

        dtoPregunta.setDescripcion("TestPregunta");
        dtoPregunta.setEstado("a");

        DtoTipoPregunta tipoPregunta = new DtoTipoPregunta(1);
        dtoPregunta.setTipo(tipoPregunta);
        DtoSubcategoria subcategoria = new DtoSubcategoria(1);
        dtoPregunta.setSubcategoria(subcategoria);

        Response respuesta = servicio.addPregunta(dtoPregunta);
        Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria utilizada para testear que se pueda eliminar una pregunta especificada por medio de su id.
     * @throws Exception En caso de encontrar alguna resticción a nivel de la base de datos.
     */
    @Test
    public void deletePreguntaTest() throws Exception
    {
        PreguntaServicio servicio = new PreguntaServicio();
        Response respuesta = servicio.deletePregunta(43);
        Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria utilizada para testear que se pueda actualizar una pregunta especificada por medio de su id.
     * @throws Exception En caso de encontrar alguna restricción a nivel de la base de datos.
     */
    @Test
    public void updatePreguntaTest() throws Exception {
        PreguntaServicio servicio = new PreguntaServicio();
        DtoPregunta dtoPregunta = new DtoPregunta();

        dtoPregunta.setDescripcion("TestPreguntaUPDATE");
        dtoPregunta.setEstado("a");

        DtoTipoPregunta tipoPregunta = new DtoTipoPregunta(2);
        dtoPregunta.setTipo(tipoPregunta);
        DtoSubcategoria subcategoria = new DtoSubcategoria(2);
        dtoPregunta.setSubcategoria(subcategoria);

        Response respuesta = servicio.updatePregunta(6,dtoPregunta);
        Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria encargada de pribar el funcionamiento del método encargado de retornar todas las preguntas
     * que tengan cierta subcategoria especificada mediante su id.
     * @throws Exception En caso de alguna restricción a nivel de la base de datos.
     */
    @Test
    public void getPreguntasbySubcategoriaTest() throws Exception
    {
        PreguntaServicio servicio = new PreguntaServicio();
        Response respuesta = servicio.getPreguntasbySubcategoria(13);
        Assert.assertEquals( respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria encargada de testear que se puedan listar todas las opciones posibles para una pregunta.
     * @throws Exception
     */
    @Test
    public void getPreguntasyOpcionesTest() throws Exception
    {
        PreguntaServicio servicio = new PreguntaServicio();
        List<PreguntaAux> respuesta = servicio.getPreguntasyOpciones(13);
        Assert.assertFalse( respuesta.isEmpty());
    }
}
