package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.PruebaExcepcion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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
            this.estudio.setSubcategoria(daoSubcategoria.find(dtoEstudio.getSubcategoria().get_id(), SubcategoriaEntity.class));
        }

        if(dtoEstudio.getNivelsocioeco() != null) {
            DaoNivelSocioeconomico daoNivelSocioeconomico = DaoFactory.DaoNivelSocioeconomicoInstancia();
            this.estudio.setNivelsocioeco(daoNivelSocioeconomico.find(dtoEstudio.getNivelsocioeco().get_id(), NivelSocioeconomicoEntity.class));
        }

        if(dtoEstudio.getLugar() != null) {
            DaoLugar daoLugar = DaoFactory.DaoLugarInstancia();
            this.estudio.setLugar(daoLugar.find(dtoEstudio.getLugar().get_id(), LugarEntity.class));
        }

        if(dtoEstudio.getAnalista() != null) {
            DaoUsuario daoAnalista = DaoFactory.DaoUsuarioInstancia();
            this.estudio.setAnalista(daoAnalista.find(dtoEstudio.getAnalista().get_id(), UsuarioEntity.class));
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

    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstudios(@PathParam("id") long id) {
        try {
            return Response.ok(this.dao.find(id, EstudioEntity.class)).build();
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
            if(dtoEstudio.get_id() == 0) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
            this.estudio = this.dao.find(dtoEstudio.get_id(), EstudioEntity.class);
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
    public Response deleteEstudio(DtoCategoria dtoCategoria) {
        try {
            this.estudio = this.dao.find(dtoCategoria.get_id(), EstudioEntity.class);
            return Response.ok(this.dao.delete(this.estudio)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }


    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/encuestado/{id}")                   //RECIBO EL ID DEL USUARIO
    public Response getEstudiosbyEncuestado(@PathParam("id") long id) throws PruebaExcepcion {

        List<EstudioEntity> estudios = null;
        try {
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoEncuestado daoEncuestado = new DaoEncuestado();
            UsuarioEntity usuarioEntity = daoUsuario.find(id, UsuarioEntity.class);

            EncuestadoEntity encuestadoEntity = daoEncuestado.getEncuestadoByUsuario(usuarioEntity);

            DaoEstudio daoEstudio = new DaoEstudio();
            estudios = daoEstudio.getEstudios(encuestadoEntity.getLugar(), encuestadoEntity.getNivelsocioeco());

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(estudios).build();
    }

}
