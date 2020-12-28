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
    //Metodo para registrar tipos
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
    //Metodo que devuelve la lista de Tipos registrados
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
    @Path("/{id}")
    //Metodo que recibiendo un ID por parametro devuelve los datos de un Tipo
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


    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    //Metodo que modifica los datos de un Tipo
    public Response updateTipo(@PathParam("id") long id, DtoTipo dtoTipo) {
        DaoTipo dao = new DaoTipo();
        TipoEntity tipo = dao.find(id, TipoEntity.class);

        if (tipo != null){
            tipo.setNombre(dtoTipo.getNombre());
            tipo.setEstado(dtoTipo.getEstado());
            tipo.setDescripcion(dtoTipo.getDescripcion());
            TipoEntity resul = dao.update(tipo);
            return Response.ok().entity(tipo).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    //Elimina un tipo recibiendo el ID por parametro
    public Response deleteTipo(@PathParam("id") long id)
    {
        try
        {
            DaoTipo dao = new DaoTipo();
            TipoEntity tipo = dao.find(id, TipoEntity.class);
            if(tipo != null) {
                TipoEntity resul = dao.delete(tipo);
                return Response.ok().build();
            }
        }
        catch (Exception er){
            String problema = er.getMessage();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
