package ucab.empresae.servicio;

import ucab.empresae.daos.DaoLugar;
import ucab.empresae.entidades.LugarEntity;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}
