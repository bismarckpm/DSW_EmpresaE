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

        ComandoRandomAnalista comandoRandomAnalista = ComandoFactory.comandoRandomAnalistaInstancia();
        this.dtoEstudio.setAnalista(comandoRandomAnalista.getResult());

        ComandoAddEstudio comandoAddEstudio = ComandoFactory.comandoAddEstudioInstancia(this.dtoEstudio);
        this.dtoEstudio = comandoAddEstudio.getResult();
        this.estudio = this.dao.find(this.dtoEstudio.get_id(), EstudioEntity.class);

        DaoCliente daoCliente = new DaoCliente();
        DaoUsuario daoUsuario = new DaoUsuario();
        if(daoCliente.getClienteByUsuario(daoUsuario.find(this.id, UsuarioEntity.class)) == null){
            throw new CustomException("COMSOLEST001","No existe ningun cliente registrado con ese Username");
        }

        ClienteEntity clienteEntity = daoCliente.getClienteByUsuario(daoUsuario.find(this.id, UsuarioEntity.class));

        ClienteEstudioEntity clienteEstudioEntity = new ClienteEstudioEntity();
        clienteEstudioEntity.setEstudio(this.estudio);
        clienteEstudioEntity.setCliente(clienteEntity);
        clienteEstudioEntity.setEstado("a");

        DaoClienteEstudio daoClienteEstudio = new DaoClienteEstudio();
        daoClienteEstudio.insert(clienteEstudioEntity);

        DaoEncuestado daoEncuestado = new DaoEncuestado();
        List<EncuestadoEntity> dataMuestraEncuestados = daoEncuestado.getDataMuestraEstudio(this.estudio.getLugar(), this.estudio.getNivelSocioEconomico());

        DaoEstudioEncuestado daoEstudioEncuestado = new DaoEstudioEncuestado();
        for(EncuestadoEntity encuestado : dataMuestraEncuestados){
            EstudioEncuestadoEntity estudioEncuestadoEntity = new EstudioEncuestadoEntity();
            estudioEncuestadoEntity.setEstado("Pendiente");
            estudioEncuestadoEntity.setEstudio(this.estudio);
            estudioEncuestadoEntity.setEncuestado(encuestado);

            daoEstudioEncuestado.insert(estudioEncuestadoEntity);
        }

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
