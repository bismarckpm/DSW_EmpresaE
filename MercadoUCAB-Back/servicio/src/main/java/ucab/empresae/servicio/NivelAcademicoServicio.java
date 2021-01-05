package ucab.empresae.servicio;

import ucab.empresae.daos.DaoNivelAcademico;
import ucab.empresae.entidades.NivelAcademicoEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad NivelAcademico
 */

@Path("/nivelacademico")
public class NivelAcademicoServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/nivelacademico
     * Metodo con anotacion GET que devuelve todos los Niveles Academicos registrados
     * @return Response con status ok con la lista de Niveles Academicos.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getNivelesAcademicos() {
        List<NivelAcademicoEntity> niveles;
        try {
            DaoNivelAcademico dao = new DaoNivelAcademico();
            niveles = dao.findAll(NivelAcademicoEntity.class);
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(niveles).build();
    }
}
