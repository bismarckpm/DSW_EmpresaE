package ucab.empresae.servicio;

import ucab.empresae.daos.DaoLugar;
import ucab.empresae.entidades.LugarEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/lugar")
public class LugarServicio {

    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    public Response getLugares(){

        List<LugarEntity> lugares = null;
        try {
            DaoLugar dao = new DaoLugar();
            lugares = dao.getLugaresByTipo("estado");
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(lugares).build();
    }

    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getLugar(@PathParam("id") long id) {

        List<LugarEntity> resultado;
        DaoLugar dao = new DaoLugar();
        LugarEntity lugar = dao.find(id, LugarEntity.class);

        if(lugar != null) {
            resultado = dao.getLugaresById(lugar);
            return Response.ok(resultado).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
