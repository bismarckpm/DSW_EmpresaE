package ucab.empresae.servicio;

import ucab.empresae.daos.DaoCategoria;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.entidades.CategoriaEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categoria")
public class CategoriaServicio extends AplicacionBase {

    private DaoCategoria dao = new DaoCategoria();
    private CategoriaEntity categoria = new CategoriaEntity();

    private void categoriAtributos(DtoCategoria dtoCategoria) {
        this.categoria.setNombre(dtoCategoria.getNombre());
        this.categoria.setEstado(dtoCategoria.getEstado());
    }

    //http://localhost:8080/servicio-1.0-SNAPSHOT/api/categoria
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getCategorias() {
        try {
            return Response.ok(this.dao.findAll(CategoriaEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getCategoria(@PathParam("id") long id) {
        try {
            return Response.ok(this.dao.find(id, CategoriaEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategoria(DtoCategoria dtoCategoria) {
        try {
            categoriAtributos(dtoCategoria);
            return Response.ok(this.dao.insert(this.categoria)).build();
        } catch(Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategoria(DtoCategoria dtoCategoria) {
        try {
            this.categoria = this.dao.find(dtoCategoria.getId(), CategoriaEntity.class);
            categoriAtributos(dtoCategoria);
            return Response.ok(this.dao.update(this.categoria)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Produces(value=MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteCategoria(DtoCategoria dtoCategoria) {
        try {
            this.dao.delete(this.dao.find(dtoCategoria.getId(), CategoriaEntity.class));
            return Response.ok().build();
        } catch(Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(ex).build();
        }
    }

}
