package ucab.empresae.servicio;

import ucab.empresae.daos.DaoSubcategoria;
import ucab.empresae.dtos.DtoSubcategoria;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.entidades.SubcategoriaEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/subcategoria")
public class SubcategoriaServicio extends AplicacionBase {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getSubCategorias() {
        List<SubcategoriaEntity> subCategorias = null;
        try {
            DaoSubcategoria dao = new DaoSubcategoria();
            subCategorias = dao.findAll(SubcategoriaEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(subCategorias).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addSubcategoria")
    public Response addSubcategoria(DtoSubcategoria dtoSubcategoria) {

        DaoSubcategoria dao = new DaoSubcategoria();
        SubcategoriaEntity subcategoria = new SubcategoriaEntity();

        if(subcategoria != null) {
            subcategoria.setNombre(dtoSubcategoria.getNombre());
            subcategoria.setEstado(dtoSubcategoria.getEstado());
            SubcategoriaEntity resul = dao.insert(subcategoria);
            return Response.ok(subcategoria).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateSubcategoria")
    public Response updateSubcategoria(DtoSubcategoria dtoSubcategoria) {

        try {
            DaoSubcategoria dao = new DaoSubcategoria();
            SubcategoriaEntity subcategoriaEntity = new SubcategoriaEntity();
            subcategoriaEntity.setNombre(dtoSubcategoria.getNombre());
            subcategoriaEntity.setEstado(dtoSubcategoria.getEstado());
            subcategoriaEntity.setCategoria(new CategoriaEntity(dtoSubcategoria.getCategoria().getId()));

            return Response.ok(dao.update(subcategoriaEntity)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}
