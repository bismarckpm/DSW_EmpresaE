package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/estudio")
public class EstudioServicio extends AplicacionBase {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstudios() {
        List<EstudioEntity> estudios = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            estudios = dao.findAll(EstudioEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(estudios).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addEstudio")
    public Response addEstudio(DtoEstudio dtoEstudio) {

        DaoEstudio dao = new DaoEstudio();
        EstudioEntity estudio = new EstudioEntity();

        if(estudio != null) {
            estudio.setNombre(dtoEstudio.getNombre());
            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setComentarioAnalista(dtoEstudio.getComentarioAnalista());
            estudio.setEdadMinima(dtoEstudio.getEdadMinima());
            estudio.setEdadMaxima(dtoEstudio.getEdadMaxima());
            estudio.setFechaInicio(dtoEstudio.getFechaInicio());
            estudio.setFechaFin(dtoEstudio.getFechaFin());

            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
            SubcategoriaEntity subCategoria = new SubcategoriaEntity(dtoEstudio.getDtoSubcategoria().getId());
            estudio.setSubcategoria(subCategoria);

            DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
            NivelSocioeconomicoEntity nivelSocioeconomico = new NivelSocioeconomicoEntity(dtoEstudio.getDtoNivelSocioEconomico().getId());
            estudio.setNivelsocioeco(nivelSocioeconomico);

            DaoLugar daoLugar = new DaoLugar();
            LugarEntity lugar = new LugarEntity(dtoEstudio.getDtoLugar().getId());
            estudio.setLugar(lugar);

            EstudioEntity resul = dao.insert(estudio);
            return Response.ok(estudio).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateEstudio/{id}")
    public DtoEstudio updateEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio) {

        DtoEstudio resultado = new DtoEstudio();
        try {
            DaoEstudio dao = new DaoEstudio();
            EstudioEntity estudio = dao.find(id, EstudioEntity.class);

            estudio.setNombre(dtoEstudio.getNombre());
            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setComentarioAnalista(dtoEstudio.getComentarioAnalista());
            estudio.setEdadMinima(dtoEstudio.getEdadMinima());
            estudio.setEdadMaxima(dtoEstudio.getEdadMaxima());
            estudio.setFechaInicio(dtoEstudio.getFechaInicio());
            estudio.setFechaFin(dtoEstudio.getFechaFin());

            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
            SubcategoriaEntity subCategoria = new SubcategoriaEntity(dtoEstudio.getDtoSubcategoria().getId());
            estudio.setSubcategoria(subCategoria);

            DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
            NivelSocioeconomicoEntity nivelSocioeconomico = new NivelSocioeconomicoEntity(dtoEstudio.getDtoNivelSocioEconomico().getId());
            estudio.setNivelsocioeco(nivelSocioeconomico);

            DaoLugar daoLugar = new DaoLugar();
            LugarEntity lugar = new LugarEntity(dtoEstudio.getDtoLugar().getId());
            estudio.setLugar(lugar);

            EstudioEntity resul = dao.update(estudio);

            resultado.setId(resul.get_id());
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }

        return resultado;
    }

}
