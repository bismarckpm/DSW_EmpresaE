import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.*;
import javax.ws.rs.core.Response;

public class EncuestadoServicio_Test {

    @Test
    public void addEncuestadoTest() throws Exception{
        ucab.empresae.servicio.EncuestadoServicio servicio = new ucab.empresae.servicio.EncuestadoServicio();
        DtoEncuestado dtoEncuestado = new DtoEncuestado();

        dtoEncuestado.setPrimerNombre("nombre1");
        dtoEncuestado.setSegundoNombre("nombre2");
        dtoEncuestado.setPrimerApellido("apellido1");
        dtoEncuestado.setSegundoApellido("apellido2");
        dtoEncuestado.setFechaNacimiento("13/03/2020");
        dtoEncuestado.setEstado("A");

        DtoEstadoCivil dtoEstadoCivil = new DtoEstadoCivil(1);
        dtoEncuestado.setEstadocivil(dtoEstadoCivil);

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

        DtoLugar dtoLugar = new DtoLugar(1045);
        dtoEncuestado.setLugar(dtoLugar);

        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setUsername("pruebaTest34545");
        dtoUsuario.setCorreoelectronico("pruebaTest3454@gmail.com");
        dtoUsuario.setClave("123456");
        dtoUsuario.setEstado("A");
        dtoEncuestado.setUsuario(dtoUsuario);

        Response respuesta = servicio.addEncuestado(dtoEncuestado);
        Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }
}
