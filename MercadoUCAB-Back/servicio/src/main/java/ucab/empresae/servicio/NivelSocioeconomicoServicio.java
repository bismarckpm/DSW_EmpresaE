package ucab.empresae.servicio;

import ucab.empresae.daos.DaoNivelSocioeconomico;
import ucab.empresae.entidades.NivelSocioeconomicoEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/nivelsocioeconomico")
public class NivelSocioeconomicoServicio {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getNivelesSocioeconomicos() {
        List<NivelSocioeconomicoEntity> niveles = null;
        try {
            DaoNivelSocioeconomico dao = new DaoNivelSocioeconomico();
            niveles = dao.findAll(NivelSocioeconomicoEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(niveles).build();
    }
}

