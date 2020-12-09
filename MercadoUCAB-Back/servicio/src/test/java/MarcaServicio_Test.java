import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoMarca;
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

    @Test
    public void addMarcaTest() throws Exception{
        ucab.empresae.servicio.MarcaServicio servicio = new ucab.empresae.servicio.MarcaServicio();
        DtoMarca dtoMarca = new DtoMarca();
        dtoMarca.setNombre("Brisa");
        dtoMarca.setEstado("A");
        Response resultado = servicio.addMarca(dtoMarca);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void updateMarcaTest() throws Exception{
        ucab.empresae.servicio.MarcaServicio servicio = new ucab.empresae.servicio.MarcaServicio();
        DtoMarca dtoMarca = new DtoMarca();
        dtoMarca.setNombre("Cola");
        dtoMarca.setEstado("A");
        Response resultado = servicio.updateMarca(7, dtoMarca);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void deleteMarcaTest() throws Exception{
        ucab.empresae.servicio.MarcaServicio servicio = new ucab.empresae.servicio.MarcaServicio();
        Response resultado = servicio.deleteMarca(7);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }
}
