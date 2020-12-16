package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date fecha = dateFormat.parse(dtoEstudio.getFechaInicio());
            this.estudio.setFechaInicio(fecha);

            fecha = dateFormat.parse(dtoEstudio.getFechaFin());
            this.estudio.setFechaFin(fecha);
        }catch (Exception ex) {
            System.out.println(ex);
        }

        if(dtoEstudio.getSubcategoria() != null) {
            DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();
            this.estudio.setSubcategoria(daoSubcategoria.find(dtoEstudio.getSubcategoria().get_id(), SubcategoriaEntity.class));
        }

        if(dtoEstudio.getNivelSocioEconomico() != null) {
            DaoNivelSocioeconomico daoNivelSocioeconomico = DaoFactory.DaoNivelSocioeconomicoInstancia();
            this.estudio.setNivelSocioEconomico(daoNivelSocioeconomico.find(dtoEstudio.getNivelSocioEconomico().get_id(), NivelSocioeconomicoEntity.class));
        }

        if(dtoEstudio.getLugar() != null) {
            DaoLugar daoLugar = new DaoLugar();
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
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(ex).build();
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

}
