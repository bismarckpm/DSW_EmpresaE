package ucab.empresae.servicio;

import ucab.empresae.daos.DaoNivelAcademico;
import ucab.empresae.entidades.NivelAcademicoEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/nivelacademico")
public class NivelAcademicoServicio {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getNivelesAcademicos() {
        List<NivelAcademicoEntity> niveles = null;
        try {
            DaoNivelAcademico dao = new DaoNivelAcademico();
            niveles = dao.findAll(NivelAcademicoEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(niveles).build();
    }
}
