package ucab.empresae.servicio;

import ucab.empresae.daos.DaoEstadoCivil;
import ucab.empresae.entidades.EstadoCivilEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad EstadoCivil
 */

@Path("/estadocivil")
public class EstadoCivilServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estadocivil
     * Metodo con anotacion GET que devuelve todos los Estados Civiles registrados
     * @return Response con status ok con la lista de Estados Civiles.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstadosCiviles() {
        List<EstadoCivilEntity> estados;
        try {
            DaoEstadoCivil dao = new DaoEstadoCivil();
            estados = dao.findAll(EstadoCivilEntity.class);
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(estados).build();
    }
}
