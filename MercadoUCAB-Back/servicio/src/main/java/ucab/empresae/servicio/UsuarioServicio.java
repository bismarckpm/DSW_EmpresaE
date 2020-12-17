package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoTipoUsuario;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.entidades.ClienteEntity;
import ucab.empresae.entidades.EncuestadoEntity;
import ucab.empresae.entidades.TipoUsuarioEntity;
import ucab.empresae.entidades.UsuarioEntity;
import ucab.empresae.excepciones.PruebaExcepcion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/usuario")
public class UsuarioServicio {


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(DtoUsuario dtoUsuario){

        DaoUsuario daoUsuario = new DaoUsuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        DaoTipoUsuario daoTipoUsuario = new DaoTipoUsuario();
        TipoUsuarioEntity tipoUsuarioEntity = daoTipoUsuario.getTipoUsuarioByDescripcion(dtoUsuario.getTipoUsuario().getDescripcion());

        usuarioEntity.setEstado(dtoUsuario.getEstado());
        usuarioEntity.setUsername(dtoUsuario.getUsername());
        usuarioEntity.setClave(dtoUsuario.getClave());
        usuarioEntity.setTipousuario(tipoUsuarioEntity);
        daoUsuario.insert(usuarioEntity);

        DirectorioActivo ldap = new DirectorioActivo();
        ldap.addEntryToLdap(dtoUsuario, tipoUsuarioEntity.getDescripcion());

        return Response.ok(usuarioEntity).build();
    }


    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getUsuario(@PathParam("id") long id) throws PruebaExcepcion {

        DaoUsuario dao = new DaoUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        UsuarioEntity usuarioEntity = dao.find(id, UsuarioEntity.class);
        Response usuario = null;

        if(usuarioEntity.getTipousuario().getDescripcion().equals("Encuestado")){

            DaoEncuestado daoEncuestado = new DaoEncuestado();
            EncuestadoEntity encuestadoEntity = daoEncuestado.getEncuestadoByUsuario(usuarioEntity);
            EncuestadoServicio encuestadoServicio = new EncuestadoServicio();
            usuario = encuestadoServicio.getEncuestado(encuestadoEntity.get_id());

        }else if(usuarioEntity.getTipousuario().getDescripcion().equals("Cliente")){

            DaoCliente daoCliente = new DaoCliente();
            ClienteEntity clienteEntity = daoCliente.getClienteByUsuario(usuarioEntity);
            ClienteServicio clienteServicio = new ClienteServicio();
            usuario = clienteServicio.getCliente(clienteEntity.get_id());

        }else if(usuarioEntity.getTipousuario().getDescripcion().equals("Administrador") ||
                usuarioEntity.getTipousuario().getDescripcion().equals("Analista")){

            dtoUsuario.set_id(usuarioEntity.get_id());
            dtoUsuario.setUsername(usuarioEntity.getUsername());

            DirectorioActivo ldap = new DirectorioActivo();
            String correo = ldap.getCorreo(dtoUsuario);
            dtoUsuario.setCorreoelectronico(correo);

            DtoTipoUsuario dtoTipoUsuario = new DtoTipoUsuario();
            dtoTipoUsuario.set_id(usuarioEntity.getTipousuario().get_id());
            dtoTipoUsuario.setDescripcion(usuarioEntity.getTipousuario().getDescripcion());
            dtoUsuario.setTipoUsuario(dtoTipoUsuario);

            usuario = Response.ok(dtoUsuario).build();

        }

        return usuario;
    }


    @GET
    @Path("/empleados")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEmpleados() {
        List<UsuarioEntity> empleados = null;
        try {
            DaoUsuario daoUsuario = new DaoUsuario();
            empleados = daoUsuario.getUsuariosEmpleados();
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(empleados).build();
    }

    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateTipo(@PathParam("id") long id, DtoUsuario dtoUsuario) {
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoTipoUsuario daoTipoUsuario = new DaoTipoUsuario();
        UsuarioEntity usuarioEntity = daoUsuario.find(id, UsuarioEntity.class);

        if (usuarioEntity != null){
            usuarioEntity.setEstado(dtoUsuario.getEstado());
            usuarioEntity.setUsername(dtoUsuario.getUsername());
            usuarioEntity.setTipousuario(daoTipoUsuario.getTipoUsuarioByDescripcion(dtoUsuario.getTipoUsuario().getDescripcion()));
            UsuarioEntity resul = daoUsuario.update(usuarioEntity);

            DirectorioActivo ldap = new DirectorioActivo();
            ldap.updateRol(dtoUsuario, usuarioEntity.getTipousuario().getDescripcion());

            return Response.ok().entity(usuarioEntity).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @DELETE
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteUsuario(@PathParam("id") long id) {

        try
        {
            DaoUsuario daoUsuario = new DaoUsuario();
            DtoUsuario dtoUsuario = new DtoUsuario();
            DirectorioActivo ldap = new DirectorioActivo();
            UsuarioEntity usuarioEntity = daoUsuario.find(id, UsuarioEntity.class);
            if(usuarioEntity != null) {

                dtoUsuario.setUsername(usuarioEntity.getUsername());
                ldap.deleteEntry(dtoUsuario);
                UsuarioEntity resul = daoUsuario.delete(usuarioEntity);

                return Response.ok().build();
            }
        }
        catch (Exception er){
            String problema = er.getMessage();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
