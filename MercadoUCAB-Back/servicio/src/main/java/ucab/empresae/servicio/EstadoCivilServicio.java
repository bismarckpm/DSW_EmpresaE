package ucab.empresae.servicio;

import ucab.empresae.daos.DaoEstadoCivil;
import ucab.empresae.entidades.EstadoCivilEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/estadocivil")
public class EstadoCivilServicio {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstadosCiviles() {
        List<EstadoCivilEntity> estados = null;
        try {
            DaoEstadoCivil dao = new DaoEstadoCivil();
            estados = dao.findAll(EstadoCivilEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(estados).build();
    }
}
