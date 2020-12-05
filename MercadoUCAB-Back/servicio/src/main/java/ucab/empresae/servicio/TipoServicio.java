package ucab.empresae.servicio;


import ucab.empresae.daos.DaoTipo;
import ucab.empresae.dtos.DtoTipo;
import ucab.empresae.entidades.TipoEntity;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tipo")
public class TipoServicio {


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addTipo")
    public Response addTipo(DtoTipo dtoTipo) {

        DaoTipo dao = new DaoTipo();
        TipoEntity tipo = new TipoEntity();

        if(tipo != null) {
            tipo.setNombre(dtoTipo.getNombre());
            tipo.setEstado(dtoTipo.getEstado());
            tipo.setDescripcion(dtoTipo.getDescripcion());
            TipoEntity resul = dao.insert(tipo);
            return Response.ok(tipo).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }


    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/getTipos")
    public Response geTipos() {
        List<TipoEntity> tipos = null;
        try {
            DaoTipo dao = new DaoTipo();
            tipos = dao.findAll(TipoEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(tipos).build();
    }


    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("getTipo/{id}")
    public Response getTipo(@PathParam("id") long id)
    {
        DaoTipo dao = new DaoTipo();
        TipoEntity tipo = dao.find(id, TipoEntity.class);

        if(tipo != null) {
            return Response.ok(tipo).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
