package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.PruebaExcepcion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Path("/estudio")
public class EstudioServicio extends AplicacionBase {

    private DaoEstudio dao = DaoFactory.DaoEstudioInstancia();
    private EstudioEntity estudio = EntidadesFactory.EstudioInstance();

    private void estudioAtributos(DtoEstudio dtoEstudio) {

        try {
            this.estudio.setEstado("Solicitado");
            this.estudio.setNombre(dtoEstudio.getNombre());
            this.estudio.setComentarioAnalista(dtoEstudio.getComentarioAnalista());
            this.estudio.setEdadMinima(dtoEstudio.getEdadMinima());
            this.estudio.setEdadMaxima(dtoEstudio.getEdadMaxima());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = dateFormat.parse(dtoEstudio.getFechaInicio());
            this.estudio.setFechaInicio(fecha);

            this.estudio.setFechaFin(null);
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

    @GET
    @Path("/cliente/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstudiosCliente(@PathParam("id") long id) {
        try {
            return Response.ok(this.dao.estudiosCliente(id)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("/analista/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstudiosAnalista(@PathParam("id") long id) {
        try {
            return Response.ok(this.dao.estudiosAnalista(id)).build();
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

    @POST
    @Path("/cliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response solicitarEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio) {
        try {
            estudioAtributos(dtoEstudio);
            DaoUsuario daoUsuario1 = new DaoUsuario();
            List<UsuarioEntity> listaAnalista = daoUsuario1.getAnalistas();
            int analistaAleatorio =  ThreadLocalRandom.current().nextInt(0, listaAnalista.size()-1);
            this.estudio.setAnalista(listaAnalista.get(analistaAleatorio));
            this.estudio = this.dao.insert(this.estudio);

            DaoClienteEstudio daoClienteEstudio = new DaoClienteEstudio();
            DaoCliente daoCliente = new DaoCliente();
            DaoUsuario daoUsuario = new DaoUsuario();
            this.estudio = this.dao.find(estudio.get_id(), EstudioEntity.class);

            ClienteEntity clienteEntity = daoCliente.getClienteByUsuario(daoUsuario.find(id, UsuarioEntity.class));

            ClienteEstudioEntity clienteEstudioEntity = new ClienteEstudioEntity();
            clienteEstudioEntity.setEstudio(this.estudio);
            clienteEstudioEntity.setCliente(clienteEntity);
            clienteEstudioEntity.setEstado("a");
            daoClienteEstudio.insert(clienteEstudioEntity);

            DaoEncuestado daoEncuestado = new DaoEncuestado();
            List<EncuestadoEntity> dataMuestraEncuestados = null;
            dataMuestraEncuestados = daoEncuestado.getDataMuestraEstudio(estudio.getLugar(), estudio.getNivelSocioEconomico());
            DaoEstudioEncuestado daoEstudioEncuestado = new DaoEstudioEncuestado();
            for(EncuestadoEntity encuestado : dataMuestraEncuestados){
                EstudioEncuestadoEntity estudioEncuestadoEntity = new EstudioEncuestadoEntity();
                estudioEncuestadoEntity.setEstado("Pendiente");
                estudioEncuestadoEntity.setEstudio(this.estudio);
                estudioEncuestadoEntity.setEncuestado(encuestado);

                daoEstudioEncuestado.insert(estudioEncuestadoEntity);
            }

            return Response.ok().build();

        } catch(Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(ex).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio) {
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = dateFormat.parse(dtoEstudio.getFechaFin());
            estudio.setFechaFin(fecha);
            estudio.setComentarioAnalista(dtoEstudio.getComentarioAnalista());
            estudio.setEstado(dtoEstudio.getEstado());
            daoEstudio.update(estudio);

            return Response.ok(estudio).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEstudio(@PathParam("id") long id) {
        try {
            this.estudio = this.dao.find(id, EstudioEntity.class);
            return Response.ok(this.dao.delete(this.estudio)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/encuestado/{id}")                   //RECIBO EL ID DEL USUARIO
    public Response getEstudiosbyEncuestado(@PathParam("id") long id) throws PruebaExcepcion {

        /*PERMITE FILTRAR LOS ESTUDIOS QUE PUEDE VER EL ENCUESTADO
        SEGUN LAS CARACTERISTICAS DEL ESTUDIO
         */

        List<EstudioEntity> estudios = null;
        try {
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoEncuestado daoEncuestado = new DaoEncuestado();
            UsuarioEntity usuarioEntity = daoUsuario.find(id, UsuarioEntity.class);

            EncuestadoEntity encuestadoEntity = daoEncuestado.getEncuestadoByUsuario(usuarioEntity);

            DaoEstudio daoEstudio = new DaoEstudio();
            estudios = daoEstudio.getEstudiosEncuestado(encuestadoEntity);

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(estudios).build();
    }

    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/dataMuestra/{id}")                   //RECIBO EL ID DEL ESTUDIO
    public Response getDataMuestraEstudio(@PathParam("id") long id) throws PruebaExcepcion {

        /*PERMITE DEVOLVER LA DATA MUESTRA ESTUDIO
         */

        List<EncuestadoEntity> dataMuestraEncuestados = null;
        try {
            DaoUsuario daoUsuario = new DaoUsuario();

            DaoEstudio daoEstudio = new DaoEstudio();
            EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

            DaoEncuestado daoEncuestado = new DaoEncuestado();
            dataMuestraEncuestados = daoEncuestado.getDataMuestraEstudio(estudio.getLugar(), estudio.getNivelSocioEconomico());

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(dataMuestraEncuestados).build();
    }

}
