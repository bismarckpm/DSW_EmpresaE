package Comandos.Estudio;

import Comandos.ComandoBase;
import Comandos.ComandoFactory;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.CustomException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ComandoSolicitarEstudio extends ComandoBase<DtoResponse> {

    private final long id;
    private DtoEstudio dtoEstudio;
    private EstudioEntity estudio = EntidadesFactory.EstudioInstance();
    private DaoEstudio dao = DaoFactory.DaoEstudioInstancia();

    public ComandoSolicitarEstudio(long id, DtoEstudio dtoEstudio) {

        this.id = id;
        this.dtoEstudio = dtoEstudio;

    }

    @Override
    public void execute() throws Exception {

        //Asignar analista de manera random al estudio solicitado
        ComandoRandomAnalista comandoRandomAnalista = ComandoFactory.comandoRandomAnalistaInstancia();
        this.dtoEstudio.setAnalista(comandoRandomAnalista.getResult());

        //Crear estudio dentro de la base de datos
        ComandoAddEstudio comandoAddEstudio = ComandoFactory.comandoAddEstudioInstancia(this.dtoEstudio);
        this.dtoEstudio = comandoAddEstudio.getResult();
        this.estudio = this.dao.find(this.dtoEstudio.get_id(), EstudioEntity.class);

        //Obtener el cliente por el id de su usuario
        DaoCliente daoCliente = DaoFactory.DaoClienteInstancia();
        DaoUsuario daoUsuario = DaoFactory.DaoUsuarioInstancia();
        ClienteEntity clienteEntity = daoCliente.getClienteByUsuario(daoUsuario.find(this.id, UsuarioEntity.class));
        if(clienteEntity == null){
            throw new CustomException("COMSOLEST001","No existe ningun cliente registrado con ese Username");
        }

        ClienteEstudioEntity clienteEstudioEntity = EntidadesFactory.ClienteEstudiosInstance();
        clienteEstudioEntity.setEstudio(this.estudio);
        clienteEstudioEntity.setCliente(clienteEntity);
        clienteEstudioEntity.setEstado("a");

        DaoClienteEstudio daoClienteEstudio = DaoFactory.DaoClienteEstudioInstancia();
        daoClienteEstudio.insert(clienteEstudioEntity);

    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Estudio solicitado");

        return dtoResponse;

    }
}
