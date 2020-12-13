package ucab.empresae.servicio;

import ucab.empresae.daos.DaoCategoria;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoSubcategoria;
import ucab.empresae.dtos.DtoSubcategoria;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.entidades.EntidadesFactory;
import ucab.empresae.entidades.SubcategoriaEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/subcategoria")
public class SubcategoriaServicio extends AplicacionBase {

    private DaoSubcategoria dao = DaoFactory.DaoSubcategoriaInstancia();
    private SubcategoriaEntity subcategoria = EntidadesFactory.SubcategoriaInstance();

    private void subcategoriaAtributos(DtoSubcategoria dtoSubcategoria) {
        this.subcategoria.setNombre(dtoSubcategoria.getNombre());
        this.subcategoria.setEstado(dtoSubcategoria.getEstado());

        DaoCategoria daoCategoria = DaoFactory.DaoCategoriaInstancia();
        this.subcategoria.setCategoria(daoCategoria.find(dtoSubcategoria.getCategoria().getId(), CategoriaEntity.class));
    }

    //http://localhost:8080/servicio-1.0-SNAPSHOT/api/subcategoria

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getSubCategorias() {
        try {
            return Response.ok(this.dao.findAll(SubcategoriaEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getSubCategoria(@PathParam("id") long id) {
        try {
            return Response.ok(this.dao.find(id, SubcategoriaEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSubcategoria(DtoSubcategoria dtoSubcategoria) {
        try {
            subcategoriaAtributos(dtoSubcategoria);
            return Response.ok(this.dao.insert(subcategoria)).build();
        } catch(Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSubcategoria(DtoSubcategoria dtoSubcategoria) {
        try {
            this.subcategoria = this.dao.find(dtoSubcategoria.getId(), SubcategoriaEntity.class);
            subcategoriaAtributos(dtoSubcategoria);
            return Response.ok(this.dao.update(this.subcategoria)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteSubcategoria(@PathParam("id") long id) {
        try {
            this.subcategoria = this.dao.delete(this.dao.find(id,SubcategoriaEntity.class));
            return Response.ok().build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}
