package ucab.empresae.servicio;

import ucab.empresae.daos.DaoEstudio;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.entidades.EstudioEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones relacionadas a los Analistas
 */

@Path("/analista")
public class AnalistaServicio extends AplicacionBase {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/analista
     * Metodo con anotacion GET que devuelve todos los Analistas registrados
     * @return Response con status ok con la lista de Analistas.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAnalistas() {

        try {
            DaoUsuario daoAnalista = new DaoUsuario();
            return Response.ok(daoAnalista.getAnalistas()).build();
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }

    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/analista/id
     * Metodo con anotacion GET que devuelve todos los Estudios asignados a un Analista a partir de su id
     * @param id identificador del Analista del que se quieren obtener todos sus Estudios
     * @return Response con status ok junto a la lista de Estudios
     */
    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAnalista(@PathParam("id") long id) {
        List<EstudioEntity> estudios;
        try {
            DaoEstudio dao = new DaoEstudio();
            estudios = dao.findAll(EstudioEntity.class);
            for (EstudioEntity obj: estudios) {
                if (obj.getAnalista().get_id() != id) {
                    estudios.remove(obj);
                }
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(estudios).build();
    }

}
