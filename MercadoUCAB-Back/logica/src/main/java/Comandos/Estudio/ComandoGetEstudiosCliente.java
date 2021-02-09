package Comandos.Estudio;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.DaoEstudio;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.excepciones.CustomException;

import java.util.List;

public class ComandoGetEstudiosCliente extends ComandoBase<DtoResponse> {

    private List<DtoEstudio> dtoEstudios;
    private final long id;

    public ComandoGetEstudiosCliente(long id) {

        this.id = id;

    }

    @Override
    public void execute() throws CustomException {

        List<EstudioEntity> estudios;
        DaoEstudio daoEstudio = DaoFactory.DaoEstudioInstancia();
        estudios = daoEstudio.estudiosCliente(this.id);

        GenericMapper estudioMapper = MapperFactory.estudioMapperInstancia();
        this.dtoEstudios = estudioMapper.CreateDtoList(estudios);

    }

    @Override
    public DtoResponse getResult() throws CustomException {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando estudios");
        dtoResponse.setObjeto(this.dtoEstudios);

        return dtoResponse;

    }

}
