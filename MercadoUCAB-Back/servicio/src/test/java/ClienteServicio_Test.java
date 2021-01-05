import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoCliente;
import ucab.empresae.dtos.DtoLugar;
import ucab.empresae.dtos.DtoTelefono;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.servicio.ClienteServicio;
import javax.ws.rs.core.Response;

public class ClienteServicio_Test {

    @Test
    //Prueba Unitaria registro de Clientes
    public void addClienteTest() throws Exception {
        ClienteServicio servicio = new ClienteServicio();
        DtoCliente dtoCliente = new DtoCliente();
        dtoCliente.setEstado("A");
        dtoCliente.setRazonSocial("Prueba Unitaria C.A");
        dtoCliente.setRif("j-1234567-8");

        DtoTelefono dtoTelefono = new DtoTelefono();
        dtoTelefono.setNumero("04142026516");
        dtoTelefono.setEstado("A");
        dtoCliente.setTelefono(dtoTelefono);

        DtoLugar dtoLugar = new DtoLugar(1338);
        dtoCliente.setLugar(dtoLugar);

        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setUsername("pruebaTest3224");
        dtoUsuario.setCorreoelectronico("pruebaTest3223@gmail.com");
        dtoUsuario.setClave("123456");
        dtoUsuario.setEstado("A");
        dtoCliente.setUsuario(dtoUsuario);

        Response resultado = servicio.addCliente(dtoCliente);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba Unitaria de la lista de Clientes
    public void getClientesTest(){
        ClienteServicio servicio = new ClienteServicio();
        Response resultado = servicio.getClientes();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }

    @Test
    //Prueba Unitaria consulta de Cliente
    public void getClienteTest(){
        ClienteServicio servicio = new ClienteServicio();
        Response resultado = servicio.getCliente(11);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba Unitaria update de Clientes
    public void updateClienteTest() throws Exception {
        ClienteServicio servicio = new ClienteServicio();
        DtoCliente dtoCliente = new DtoCliente();
        dtoCliente.setEstado("A");
        dtoCliente.setRazonSocial("Prueba Unitaria Update C.A");

        DtoTelefono dtoTelefono = new DtoTelefono();
        dtoTelefono.setNumero("04142026516");
        dtoCliente.setTelefono(dtoTelefono);

        DtoLugar dtoLugar = new DtoLugar(1339);
        dtoCliente.setLugar(dtoLugar);

        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setUsername("pruebaTest3224");
        dtoUsuario.setCorreoelectronico("pruebaTest322344@gmail.com");
        dtoCliente.setUsuario(dtoUsuario);

        Response resultado = servicio.updateCliente(11, dtoCliente);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba Unitaria update del estado del Cliente
    public void updateEstadoClienteTest(){
        ClienteServicio servicio = new ClienteServicio();
        DtoCliente dtoCliente = new DtoCliente();
        dtoCliente.setEstado("I");

        Response resultado = servicio.updateEstadoCliente(11, dtoCliente);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba Unitaria para la eliminacion de un Cliente
    public void deleteClienteTest(){
        ClienteServicio servicio = new ClienteServicio();
        Response resultado = servicio.deleteCliente(11);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }
}
