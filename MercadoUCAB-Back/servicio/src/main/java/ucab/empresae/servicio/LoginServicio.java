package ucab.empresae.servicio;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.entidades.TipoUsuarioEntity;
import ucab.empresae.entidades.UsuarioEntity;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        JsonObject respuesta= Json.createObjectBuilder().build();

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
}
