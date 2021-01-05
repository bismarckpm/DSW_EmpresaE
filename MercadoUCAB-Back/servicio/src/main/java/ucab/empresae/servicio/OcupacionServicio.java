package ucab.empresae.servicio;

import ucab.empresae.daos.DaoOcupacion;
import ucab.empresae.entidades.OcupacionEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad Ocupacion
 */

@Path("/ocupacion")
public class OcupacionServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/ocupacion
     * Metodo con anotacion GET que devuelve todas las ocupaciones registradas
     * @return Response con status ok con la lista de Ocupaciones.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getOcupaciones() {
        List<OcupacionEntity> ocupaciones;
        try {
            DaoOcupacion dao = new DaoOcupacion();
            ocupaciones = dao.findAll(OcupacionEntity.class);
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(ocupaciones).build();
    }
}
