import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoMarca;
import ucab.empresae.servicio.MarcaServicio;

import javax.ws.rs.core.Response;

/**
 * Pruebas unitarias utilizadad para testear el funcionamineto de MarcaServicio
 * @see MarcaServicio Servicio a probar.
 */
public class MarcaServicio_Test {

    /**
     * Prueba unitaria utilizada para probar que se puedan listar todas las marcas registradas en sistema.
     */
    @Test
    //Prueba Unitaria de la lista de Marcas
    public void getMarcasTest(){
        MarcaServicio servicio = new MarcaServicio();
        Response resultado = servicio.getMarcas();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria utilizada para obtener una marca en específico refistrada en la base de datos.
     */
    @Test
    //Prueba Unitaria consulta de Marca
    public void getMarcaTest(){
        MarcaServicio servicio = new MarcaServicio();
        Response resultado = servicio.getMarca(2);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria utilizada para pribar que se pueda añadir al sistema una marca nueva.
     */
    @Test
    //Prueba Unitaria registro de Marcas
    public void addMarcaTest(){
        MarcaServicio servicio = new MarcaServicio();
        DtoMarca dtoMarca = new DtoMarca();
        dtoMarca.setNombre("Prueba");
        dtoMarca.setEstado("A");
        Response resultado = servicio.addMarca(dtoMarca);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria utilizada para testear que se puedan actualizar los datos de una marca especificada.
     */
    @Test
    //Prueba Unitaria modificacion de Marcas
    public void updateMarcaTest(){
        MarcaServicio servicio = new MarcaServicio();
        DtoMarca dtoMarca = new DtoMarca();
        dtoMarca.setNombre("Cola");
        dtoMarca.setEstado("A");
        Response resultado = servicio.updateMarca(2, dtoMarca);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria utilizada para testear que se pueda eliminar una marca.
     */
    @Test
    //Prueba Unitaria para la eliminacion de una Marca
    public void deleteMarcaTest(){
        MarcaServicio servicio = new MarcaServicio();
        Response resultado = servicio.deleteMarca(2);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }
}
