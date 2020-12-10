package ucab.empresae.servicio;

import ucab.empresae.daos.DaoMedioConexion;
import ucab.empresae.entidades.MedioConexionEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class MedioConexionServicio {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getMediosConexion() {
        List<MedioConexionEntity> mediosDeConexion = null;
        try {
            DaoMedioConexion dao = new DaoMedioConexion();
            mediosDeConexion = dao.findAll(MedioConexionEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(mediosDeConexion).build();
    }
}
