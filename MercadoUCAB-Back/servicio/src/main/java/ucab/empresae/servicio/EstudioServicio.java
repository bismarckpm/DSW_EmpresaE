package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/estudio")
public class EstudioServicio extends AplicacionBase {

    private DaoEstudio dao = DaoFactory.DaoEstudioInstancia();
    private EstudioEntity estudio = EntidadesFactory.EstudioInstance();

    private void estudioAtributos(DtoEstudio dtoEstudio) {
        this.estudio.setEstado(dtoEstudio.getEstado());
        this.estudio.setNombre(dtoEstudio.getNombre());
        this.estudio.setComentarioAnalista(dtoEstudio.getComentarioAnalista());
        this.estudio.setEdadMinima(dtoEstudio.getEdadMinima());
        this.estudio.setEdadMaxima(dtoEstudio.getEdadMaxima());
        this.estudio.setFechaInicio(dtoEstudio.getFechaInicio());
        this.estudio.setFechaFin(dtoEstudio.getFechaFin());

        if(dtoEstudio.getSubcategoria() != null) {
            DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();
            this.estudio.setSubcategoria(daoSubcategoria.find(dtoEstudio.getSubcategoria().getId(), SubcategoriaEntity.class));
        }

        if(dtoEstudio.getNivelsocioeco() != null) {
            DaoNivelSocioeconomico daoNivelSocioeconomico = DaoFactory.DaoNivelSocioeconomicoInstancia();
            this.estudio.setNivelsocioeco(daoNivelSocioeconomico.find(dtoEstudio.getNivelsocioeco().getId(), NivelSocioeconomicoEntity.class));
        }

        if(dtoEstudio.getLugar() != null) {
            DaoLugar daoLugar = DaoFactory.DaoLugarInstancia();
            this.estudio.setLugar(daoLugar.find(dtoEstudio.getLugar().getId(), LugarEntity.class));
        }

        if(dtoEstudio.getAnalista() != null) {
            DaoUsuario daoAnalista = DaoFactory.DaoUsuarioInstancia();
            this.estudio.setAnalista(daoAnalista.find(dtoEstudio.getAnalista().getId(), UsuarioEntity.class));
        }
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstudios() {
        try {
            return Response.ok(this.dao.findAll(EstudioEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEstudio(DtoEstudio dtoEstudio) {
        try {
            estudioAtributos(dtoEstudio);
            return Response.ok(this.dao.insert(this.estudio)).build();
        } catch(Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEstudio(DtoEstudio dtoEstudio) {
        try {
            if(dtoEstudio.getId() == 0) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
            this.estudio = this.dao.find(dtoEstudio.getId(), EstudioEntity.class);
            estudioAtributos(dtoEstudio);
            return Response.ok(this.dao.update(this.estudio)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEstudio(DtoEstudio dtoEstudio) {
        try {
            this.estudio = this.dao.find(dtoEstudio.getId(), EstudioEntity.class);
            return Response.ok(this.dao.delete(this.estudio)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}
