import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoMarca;
import javax.ws.rs.core.Response;
import ucab.empresae.servicio.MarcaServicio;


public class MarcaServicio_Test {

    @Test
    //Prueba Unitaria de la lista de Marcas
    public void getMarcasTest(){
        MarcaServicio servicio = new MarcaServicio();
        Response resultado = servicio.getMarcas();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }

    @Test
    //Prueba Unitaria consulta de Marca
    public void getMarcaTest(){
        MarcaServicio servicio = new MarcaServicio();
        Response resultado = servicio.getMarca(1);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba Unitaria registro de Marcas
    public void addMarcaTest(){
        MarcaServicio servicio = new MarcaServicio();
        DtoMarca dtoMarca = new DtoMarca();
        dtoMarca.setNombre("Brisa");
        dtoMarca.setEstado("A");
        Response resultado = servicio.addMarca(dtoMarca);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba Unitaria modificacion de Marcas
    public void updateMarcaTest(){
        MarcaServicio servicio = new MarcaServicio();
        DtoMarca dtoMarca = new DtoMarca();
        dtoMarca.setNombre("Cola");
        dtoMarca.setEstado("A");
        Response resultado = servicio.updateMarca(7, dtoMarca);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba Unitaria para la eliminacion de una Marca
    public void deleteMarcaTest(){
        MarcaServicio servicio = new MarcaServicio();
        Response resultado = servicio.deleteMarca(7);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }
}
