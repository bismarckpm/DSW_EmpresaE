package ucab.empresae.servicio;

import ucab.empresae.daos.DaoCategoria;
import ucab.empresae.daos.DaoEstudio;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.entidades.UsuarioEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/analista")
public class AnalistaServicio extends AplicacionBase {


    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAnalistas() {

        try {
            DaoUsuario daoAnalista = new DaoUsuario();
            return Response.ok(daoAnalista.getAnalistas()).build();
        } catch (Exception ex) {
            return null;
        }

    }

    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAnalista(@PathParam("id") long id) {
        List<EstudioEntity> estudios = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            estudios = dao.findAll(EstudioEntity.class);
            for (EstudioEntity obj: estudios) {
                if (obj.getAnalista().get_id() != id) {
                    estudios.remove(obj);
                }
            }
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(estudios).build();
    }

}
