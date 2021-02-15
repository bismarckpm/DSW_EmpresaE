package ucab.empresae.servicio;

import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.entidades.UsuarioEntity;
import ucab.empresae.excepciones.UsuarioException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * API service encargada de realizar transacciones relacionadas con la autenticacion de usuarios
 */

@Path("/login")
public class LoginServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/login
     * Metodo con anotacion POST que recibe un DtoUsuario y valida que el usuario se encuentre registrado en el sistema
     * @param dtoUsuario objeto que posee todos los atributos necesarios para validar las credenciales del usuario
     * @return Response de status OK junto con la respuesta a la autenticacion del usuario
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response autenticacionUsuario(DtoUsuario dtoUsuario){

        try{
            boolean registrado;

            DaoUsuario daoUsuario = new DaoUsuario();
            DirectorioActivo directorioActivo = new DirectorioActivo();
            UsuarioEntity usuario;
            JsonObject respuesta;

            registrado = directorioActivo.userAuthentication(dtoUsuario);
            usuario = daoUsuario.getUsuarioByUsername(dtoUsuario.getUsername());

            if(registrado && usuario.getEstado().equals("a")){
                respuesta = Json.createObjectBuilder()
                        .add("autenticacion", "valida")
                        .add("_id", usuario.get_id())
                        .add("username", usuario.getUsername())
                        .add("rol", usuario.getTipousuario().getDescripcion()).build();
            }else{
                respuesta = Json.createObjectBuilder()
                        .add("autenticacion", "invalida").build();
            }
            return Response.ok().entity(respuesta).build();

        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/login/cambiarClave
     * Metodo con anotacion POST que recibe un DtoUsuario y cambia la clave del usuario
     * @param usuario objeto que posee todos los atributos necesarios para cambiar la clave de un usuario
     * @return Response de status OK junto con la respuesta al cambio de clave (Satisfactorio/Invalido)
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cambiarClave")
    public Response cambiarClave(DtoUsuario usuario){

        try{
            boolean registrado;
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
            return Response.ok().entity(respuesta).build();

        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/login/recuperarClave
     * Metodo con anotacion POST que recibe un DtoUsuario para recuperar la clave del usuario
     * @param dtoUsuario objeto que posee todos los atributos necesarios para la recuperacion de clave
     * @return Response de status OK junto con la respuesta a la recuperacion de la clave
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/recuperarClave")
    public Response recuperarClave(DtoUsuario dtoUsuario){

        JsonObject respuesta;
        String contenido;
        DirectorioActivo directorioActivo = new DirectorioActivo();

        try {
            DirectorioActivo ldap = new DirectorioActivo();
            String correo = ldap.getCorreo(dtoUsuario);

            String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            String nuevaClave = "";
            for (int x = 0; x < 6; x++) {
                int indiceAleatorio =  ThreadLocalRandom.current().nextInt(0, 62);
                char caracterAleatorio = banco.charAt(indiceAleatorio);
                nuevaClave += caracterAleatorio;
            }

            DaoUsuario daoUsuario = new DaoUsuario();

            if(daoUsuario.getUsuarioByUsername(dtoUsuario.getUsername()) == null){
                throw new UsuarioException("No existe un usuario con ese Username registrado");
            }

            UsuarioEntity usuarioEntity = daoUsuario.getUsuarioByUsername(dtoUsuario.getUsername());
            usuarioEntity.setClave(nuevaClave);
            daoUsuario.update(usuarioEntity);

            DtoUsuario usuario = new DtoUsuario();
            usuario.setUsername(dtoUsuario.getUsername());
            usuario.setClave(nuevaClave);
            directorioActivo.changePassword(usuario);

            contenido = "Estimado "+dtoUsuario.getUsername()+" le notificamos que su nueva clave para ingresar en MercadeoUCAB es la siguiente: "+nuevaClave;
            enviarCorreo(correo, "Recuperacion de Clave MercadeoUCAB", contenido);
            respuesta = Json.createObjectBuilder().add("status","Correo Enviado Satisfactoriamente").build();

            return Response.ok().entity(respuesta).build();

        }catch (UsuarioException ex) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage()).build();
            return Response.status(500).entity(excepcion).build();
        }
        catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }


    /**
     * Metodo void que se encarga de enviar un correo electronico al email de un usuario
     * @param destinatario email del usuario
     * @param asunto asunto del correo
     * @param contenido contenido del correo que se enviara al usuario
     */
    public void enviarCorreo(String destinatario, String asunto, String contenido){

        try{
            Properties props = new Properties();

            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("from", "soportemercadeoucab@gmail.com");
            props.put("username", "soportemercadeoucab@gmail.com");
            props.put("password", "mercadeoucab");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty("from")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(contenido);
            Transport.send(message);
        }catch (Exception ex) {
            String problema = ex.getMessage();
        }

    }

}
