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

import java.util.List;

public class ComandoGetEstudiosAnalista extends ComandoBase<DtoResponse> {

    private final long id;
    private List<DtoEstudio> dtoEstudios;

    public ComandoGetEstudiosAnalista(long id) {

        this.id = id;

    }

    @Override
    public void execute() throws Exception {

        List<EstudioEntity> estudios;

        DaoEstudio daoEstudio = DaoFactory.DaoEstudioInstancia();
        estudios = daoEstudio.estudiosAnalista(this.id);

        GenericMapper estudioMapper = MapperFactory.estudioMapperInstancia();
        this.dtoEstudios = estudioMapper.CreateDtoList(estudios);

    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando estudios");
        dtoResponse.setObjeto(this.dtoEstudios);

        return dtoResponse;

    }
}
