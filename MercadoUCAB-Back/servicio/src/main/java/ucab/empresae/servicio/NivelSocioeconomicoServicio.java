package ucab.empresae.servicio;

import ucab.empresae.daos.DaoNivelSocioeconomico;
import ucab.empresae.entidades.NivelSocioeconomicoEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad NivelSocioeconomico
 */

@Path("/nivelsocioeconomico")
public class NivelSocioeconomicoServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/nivelsocioeconomico
     * Metodo con anotacion GET que devuelve todos los Niveles Socioeconomicos registrados
     * @return Response con status ok con la lista de Niveles Socioeconomicos.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getNivelesSocioeconomicos() {
        List<NivelSocioeconomicoEntity> niveles;
        try {
            DaoNivelSocioeconomico dao = new DaoNivelSocioeconomico();
            niveles = dao.findAll(NivelSocioeconomicoEntity.class);
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(niveles).build();
    }
}

