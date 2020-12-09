package ucab.empresae.servicio;

import ucab.empresae.daos.DaoCategoria;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.entidades.CategoriaEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categoria")
public class CategoriaServicio extends AplicacionBase {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getCategorias() {
        List<CategoriaEntity> categorias = null;
        try {
            DaoCategoria dao = new DaoCategoria();
            categorias = dao.findAll(CategoriaEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(categorias).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addCategoria")
    public Response addCategoria(DtoCategoria dtoCategoria) {

        DaoCategoria dao = new DaoCategoria();
        CategoriaEntity categoria = new CategoriaEntity();

        if(categoria != null) {
            categoria.setNombre(dtoCategoria.getNombre());
            categoria.setEstado(dtoCategoria.getEstado());
            CategoriaEntity resul = dao.insert(categoria);
            return Response.ok(categoria).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateCategoria")
    public Response updateCategoria(DtoCategoria dtoCategoria) {

        try {
            DaoCategoria dao = new DaoCategoria();
            CategoriaEntity categoria = new CategoriaEntity();
            categoria.setNombre(dtoCategoria.getNombre());
            categoria.setEstado(dtoCategoria.getEstado());
            CategoriaEntity resul = dao.update(categoria);
            return Response.ok(categoria).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}
