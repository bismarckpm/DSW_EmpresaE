package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.*;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cliente")
public class ClienteServicio {

    @POST
    public Response addCliente(DtoCliente dtoCliente){

        String rol = "Cliente";
        long tipoUsuario = 2;

        DaoCliente daoCliente = new DaoCliente();
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setEstado(dtoCliente.getEstado());
        clienteEntity.setRazonSocial(dtoCliente.getRazonSocial());
        clienteEntity.setRif(dtoCliente.getRif());

        DaoLugar daoLugar = new DaoLugar();
        LugarEntity lugarEntity = daoLugar.find(dtoCliente.getLugar().get_id(), LugarEntity.class);
        clienteEntity.setLugar(lugarEntity);

        DaoTipoUsuario daoTipoUsuario = new DaoTipoUsuario();
        DaoUsuario daoUsuario = new DaoUsuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setUsername(dtoCliente.getUsuario().getUsername());
        usuarioEntity.setClave(dtoCliente.getUsuario().getClave());
        usuarioEntity.setEstado("a");
        usuarioEntity.setTipousuario(daoTipoUsuario.find(tipoUsuario, TipoUsuarioEntity.class));
        daoUsuario.insert(usuarioEntity);

        clienteEntity.setUsuario(usuarioEntity);
        daoCliente.insert(clienteEntity);

        DaoTelefono daoTelefono = new DaoTelefono();
        TelefonoEntity telefonoEntity = new TelefonoEntity();
        telefonoEntity.setNumero(dtoCliente.getTelefono().getNumero());
        telefonoEntity.setEstado("a");
        telefonoEntity.setCliente(clienteEntity);
        daoTelefono.insert(telefonoEntity);

        DirectorioActivo ldap = new DirectorioActivo();
        ldap.addEntryToLdap( dtoCliente.getUsuario(), rol );

        return Response.ok(clienteEntity).build();
    }


    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getClientes() {
        List<ClienteEntity> clientes = null;
        try {
            DaoCliente dao = new DaoCliente();
            clientes = dao.findAll(ClienteEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(clientes).build();
    }

    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getCliente(@PathParam("id") long id) throws PruebaExcepcion {
        DaoCliente dao = new DaoCliente();
        ClienteEntity clienteEntity = dao.find(id, ClienteEntity.class);


        if(clienteEntity != null) {
            DtoCliente dtoCliente = new DtoCliente();
            dtoCliente.set_id(clienteEntity.get_id());
            dtoCliente.setEstado(clienteEntity.getEstado());
            dtoCliente.setRazonSocial(clienteEntity.getRazonSocial());
            dtoCliente.setRif(clienteEntity.getRif());

            DtoLugar dtoParroquia = new DtoLugar();
            dtoParroquia.set_id(clienteEntity.getLugar().get_id());
            dtoParroquia.setEstado(clienteEntity.getLugar().getEstado());
            dtoParroquia.setNombre(clienteEntity.getLugar().getNombre());
            dtoParroquia.setTipo(clienteEntity.getLugar().getTipo());

            DtoLugar dtoMunicipio = new DtoLugar();
            dtoMunicipio.set_id(clienteEntity.getLugar().getLugar().get_id());
            dtoMunicipio.setEstado(clienteEntity.getLugar().getLugar().getEstado());
            dtoMunicipio.setNombre(clienteEntity.getLugar().getLugar().getNombre());
            dtoMunicipio.setTipo(clienteEntity.getLugar().getLugar().getTipo());
            dtoMunicipio.setLugar(dtoParroquia);

            DtoLugar dtoPais = new DtoLugar();
            dtoPais.set_id(clienteEntity.getLugar().getLugar().getLugar().get_id());
            dtoPais.setEstado(clienteEntity.getLugar().getLugar().getLugar().getEstado());
            dtoPais.setNombre(clienteEntity.getLugar().getLugar().getLugar().getNombre());
            dtoPais.setTipo(clienteEntity.getLugar().getLugar().getLugar().getTipo());
            dtoPais.setLugar(dtoMunicipio);

            dtoCliente.setLugar(dtoPais);

            DtoUsuario dtoUsuario = new DtoUsuario();
            dtoUsuario.set_id(clienteEntity.getUsuario().get_id());
            dtoUsuario.setEstado(clienteEntity.getUsuario().getEstado());
            dtoUsuario.setUsername(clienteEntity.getUsuario().getUsername());

            DirectorioActivo ldap = new DirectorioActivo();
            String correo = ldap.getCorreo(dtoUsuario);
            dtoUsuario.setCorreoelectronico(correo);

            dtoCliente.setUsuario(dtoUsuario);

            DaoTelefono daoTelefono = new DaoTelefono();
            TelefonoEntity telefonoEntity = daoTelefono.getTelefonoByCliente(clienteEntity);
            DtoTelefono dtoTelefono = new DtoTelefono();
            dtoTelefono.set_id(telefonoEntity.get_id());
            dtoTelefono.setNumero(telefonoEntity.getNumero());

            dtoCliente.setTelefono(dtoTelefono);

            return Response.ok(dtoCliente).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteCliente(@PathParam("id") long id){

        try{
            DaoCliente daoCliente = new DaoCliente();
            ClienteEntity clienteEntity = daoCliente.find(id, ClienteEntity.class);

            DaoUsuario daoUsuario = new DaoUsuario();
            UsuarioEntity usuarioEntity = daoUsuario.find(clienteEntity.getUsuario().get_id(), UsuarioEntity.class);

            DaoTelefono daoTelefono = new DaoTelefono();
            TelefonoEntity telefonoEntity = daoTelefono.getTelefonoByCliente(clienteEntity);
            daoTelefono.delete(telefonoEntity);

            ClienteEntity clienteResul = daoCliente.delete(clienteEntity);
            UsuarioEntity usuarioResul = daoUsuario.delete(usuarioEntity);

            return Response.ok().build();

        }catch (Exception ex){
            String problema = ex.getMessage();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateCliente(@PathParam("id") long id, DtoCliente dtoCliente) {
        DaoCliente dao = new DaoCliente();
        ClienteEntity clienteEntity = dao.find(id, ClienteEntity.class);

        if (clienteEntity != null){

            clienteEntity.setEstado(dtoCliente.getEstado());
            clienteEntity.setRazonSocial(dtoCliente.getRazonSocial());

            DaoLugar daoLugar = new DaoLugar();
            LugarEntity lugarEntity = daoLugar.find(dtoCliente.getLugar().get_id(), LugarEntity.class);
            clienteEntity.setLugar(lugarEntity);

            DaoTelefono daoTelefono = new DaoTelefono();
            TelefonoEntity telefonoEntity = daoTelefono.getTelefonoByCliente(clienteEntity);
            telefonoEntity.setNumero(dtoCliente.getTelefono().getNumero());
            telefonoEntity.setEstado("a");
            telefonoEntity.setCliente(clienteEntity);
            TelefonoEntity resulTlf = daoTelefono.update(telefonoEntity);


            ClienteEntity resul = dao.update(clienteEntity);
            return Response.ok().entity(clienteEntity).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
