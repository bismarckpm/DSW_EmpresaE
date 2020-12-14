package ucab.empresae.servicio;

import org.codehaus.jettison.json.JSONException;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.entidades.UsuarioEntity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/login")
public class LoginServicio {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject autenticacionUsuario(DtoUsuario dtoUsuario) throws JSONException {

        Boolean registrado;

        DaoUsuario daoUsuario = new DaoUsuario();
        DirectorioActivo directorioActivo = new DirectorioActivo();
        UsuarioEntity usuario;
        JsonObject respuesta;

        registrado = directorioActivo.userAuthentication(dtoUsuario);

        if(registrado){
            usuario = daoUsuario.getUsuarioByUsername(dtoUsuario.getUsername());
            respuesta = Json.createObjectBuilder()
                    .add("autenticacion", "valida")
                    .add("_id", usuario.get_id())
                    .add("username", usuario.getUsername())
                    .add("rol", usuario.getTipousuario().getDescripcion()).build();
        }else{
            respuesta = Json.createObjectBuilder()
                    .add("autenticacion", "invalida").build();
        }

        return respuesta;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cambiarClave")
    public JsonObject cambiarClave(DtoUsuario usuario){

        Boolean registrado;
        JsonObject respuesta;

        DirectorioActivo directorioActivo = new DirectorioActivo();

        DaoUsuario daoUsuario = new DaoUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        UsuarioEntity usuarioEntity = daoUsuario.find(usuario.get_id(), UsuarioEntity.class);
        dtoUsuario.setUsername(usuarioEntity.getUsername());
        dtoUsuario.setClave(usuario.getClave());
        registrado = directorioActivo.userAuthentication(dtoUsuario);

        if(registrado){
            dtoUsuario.setClave(usuario.getNuevaClave());
            directorioActivo.changePassword(dtoUsuario);
            usuarioEntity.setClave(usuario.getNuevaClave());
            daoUsuario.update(usuarioEntity);
            respuesta = Json.createObjectBuilder().add("Respuesta", "Cambio Satisfactorio").build();
        }else{
            respuesta = Json.createObjectBuilder().add("Respuesta", "Contraseña invalida").build();
        }

        return respuesta;
    }
}
