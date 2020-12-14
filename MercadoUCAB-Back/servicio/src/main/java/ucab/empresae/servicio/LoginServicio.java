package ucab.empresae.servicio;

import org.codehaus.jettison.json.JSONException;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.entidades.UsuarioEntity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.mail.*;
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/recuperarClave")
    public JsonObject recuperarClave(DtoUsuario dtoUsuario){

        Boolean registrado;
        JsonObject respuesta = null;
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

        }catch (Exception ex){
            String problema = ex.getMessage();
        }
        return respuesta;
    }



    public void enviarCorreo(String destinatario, String asunto, String contenido) throws MessagingException {

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
    }

}
