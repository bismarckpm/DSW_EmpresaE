package ucab.empresae.servicio;

import ucab.empresae.daos.DaoMedioConexion;
import ucab.empresae.entidades.MedioConexionEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad MedioConexion
 */

@Path("/medioconexion")
public class MedioConexionServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/medioconexion
     * Metodo con anotacion GET que devuelve todos los Medios de Conexion registrados
     * @return Response con status ok con la lista de Medios de Conexion.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getMediosConexion() {
        List<MedioConexionEntity> mediosDeConexion;
        try {
            DaoMedioConexion dao = new DaoMedioConexion();
            mediosDeConexion = dao.findAll(MedioConexionEntity.class);
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(mediosDeConexion).build();
    }
}
