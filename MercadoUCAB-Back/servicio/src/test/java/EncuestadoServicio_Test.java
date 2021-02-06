import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.*;
import javax.ws.rs.core.Response;
import ucab.empresae.servicio.EncuestadoServicio;

/**
 * Pruebas unitarias utilizadas para pribar el servicio de encuestado.
 * @see EncuestadoServicio Servicio a probar.
 */
public class EncuestadoServicio_Test {

    /**
     * Prueba unitaria utilizada para agregar un encuestado nuevo a la base de datos.
     * @throws Exception En caso de encontrar algún constraint a nivel de base de datos que impida agregar al encuestado.
     */
    @Test
    //Prueba Unitaria registro de Encuestados
    public void addEncuestadoTest() throws Exception{
        EncuestadoServicio servicio = new EncuestadoServicio();
        DtoEncuestado dtoEncuestado = new DtoEncuestado();

        dtoEncuestado.setPrimerNombre("nombre1");
        dtoEncuestado.setSegundoNombre("nombre2");
        dtoEncuestado.setPrimerApellido("apellido1");
        dtoEncuestado.setSegundoApellido("apellido2");
        dtoEncuestado.setFechaNacimiento("2020-03-12");
        dtoEncuestado.setEstado("A");

        DtoEstadoCivil dtoEstadoCivil = new DtoEstadoCivil(1);
        dtoEncuestado.setEstadoCivil(dtoEstadoCivil);

        DtoNivelAcademico dtoNivelAcademico = new DtoNivelAcademico(1);
        dtoEncuestado.setNivelAcademico(dtoNivelAcademico);

        DtoMedioConexion dtoMedioConexion = new DtoMedioConexion(1);
        dtoEncuestado.setMedioConexion(dtoMedioConexion);

        DtoGenero dtoGenero = new DtoGenero(1);
        dtoEncuestado.setGenero(dtoGenero);

        DtoOcupacion dtoOcupacion = new DtoOcupacion(1);
        dtoEncuestado.setOcupacion(dtoOcupacion);

        DtoNivelSocioEconomico dtoNivelSocioEconomico = new DtoNivelSocioEconomico(1);
        dtoEncuestado.setNivelSocioEconomico(dtoNivelSocioEconomico);

        DtoLugar dtoLugar = new DtoLugar(1338);
        dtoEncuestado.setLugar(dtoLugar);

        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setUsername("pruebaTest34546");
        dtoUsuario.setCorreoelectronico("pruebaTest3454@gmail.com");
        dtoUsuario.setClave("123456");
        dtoUsuario.setEstado("A");
        dtoEncuestado.setUsuario(dtoUsuario);

        DtoTelefono dtoTelefono = new DtoTelefono();
        dtoTelefono.setNumero("04122027898");
        dtoEncuestado.setTelefono(dtoTelefono);

        Response respuesta = servicio.addEncuestado(dtoEncuestado);
        Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria que prueba obtener un encuestado por medio de su id
     * @throws Exception En caso de encontrar algún constraint a nivel de base de datos.
     */
    @Test
    //Prueba Unitaria consulta de encuestados
    public void getEncuestadoTest() throws Exception{
        EncuestadoServicio servicio = new EncuestadoServicio();
        Response resultado = servicio.getEncuestado(17);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria que prueba que se puedan listar todos los encuestados registrados en el sistema.
     */
    @Test
    //Prueba Unitaria lista de encuestados
    public void getEncuestadosTest(){
        EncuestadoServicio servicio = new EncuestadoServicio();
        Response resultado = servicio.getEncuestados();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria que prueba que se pueda eliminar algún encuestado especificado mediante su id.
     */
    @Test
    //Prueba Unitaria de la eliminacion de encuestados
    public void deleteEncuestadoTest(){
        EncuestadoServicio servicio = new EncuestadoServicio();
        Response resultado = servicio.deleteEncuestado(17);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }


}
