package ucab.empresae.servicio;


import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoMarca;
import ucab.empresae.dtos.DtoMarca;
import ucab.empresae.entidades.MarcaEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/marca")
public class MarcaServicio {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addMarca")
    public Response addMarca(DtoMarca dtoMarca) {

        DaoMarca dao = new DaoMarca();
        MarcaEntity marca = new MarcaEntity();

        if(marca != null) {
            marca.setNombre(dtoMarca.getNombre());
            marca.setEstado(dtoMarca.getEstado());
            MarcaEntity resul = dao.insert(marca);
            return Response.ok(marca).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }


    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/getMarcas")
    public Response getMarcas() {
        List<MarcaEntity> marcas = null;
        try {
            DaoMarca dao = new DaoMarca();
            marcas = dao.findAll(MarcaEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(marcas).build();
    }


    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("getMarca/{id}")
    public Response getMarca(@PathParam("id") long id)
    {
        DaoMarca dao = new DaoMarca();
        MarcaEntity marca = dao.find(id, MarcaEntity.class);

        if(marca != null) {
            return Response.ok(marca).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/updateMarca/{id}")
    public Response updateMarca(@PathParam("id") long id, DtoMarca dtoMarca) {
        DaoMarca dao = new DaoMarca();
        MarcaEntity marca = dao.find(id, MarcaEntity.class);

        if (marca != null){
            marca.setNombre(dtoMarca.getNombre());
            marca.setEstado(dtoMarca.getEstado());
            MarcaEntity resul = dao.update(marca);
            return Response.ok().entity(marca).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("deleteMarca/{id}")
    public Response deleteMarca(@PathParam("id") long id)
    {
        try
        {
            DaoMarca dao = new DaoMarca();
            MarcaEntity marca = dao.find(id, MarcaEntity.class);
            if(marca != null) {
                MarcaEntity resul = dao.delete(marca);
                return Response.ok().build();
            }
        }
        catch (Exception er){
            String problema = er.getMessage();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
