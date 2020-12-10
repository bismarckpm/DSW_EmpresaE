package ucab.empresae.servicio;

import ucab.empresae.daos.DaoLugar;
import ucab.empresae.daos.DaoMarca;
import ucab.empresae.entidades.LugarEntity;
import ucab.empresae.entidades.MarcaEntity;

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
    public Response getLugares(){

        List<LugarEntity> lugares = null;
        try {
            DaoLugar dao = new DaoLugar();
            lugares = dao.findAll(LugarEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(lugares).build();
    }

    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getLugar(@PathParam("id") long id)
    {
        DaoLugar dao = new DaoLugar();
        LugarEntity lugar = dao.find(id, LugarEntity.class);

        if(lugar != null) {
            return Response.ok(lugar).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
