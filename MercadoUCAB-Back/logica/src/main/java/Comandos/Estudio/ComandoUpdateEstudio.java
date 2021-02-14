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

import java.util.Date;

public class ComandoUpdateEstudio extends ComandoBase<DtoResponse> {

    private DtoEstudio dtoEstudio;

    public ComandoUpdateEstudio(DtoEstudio dtoEstudio) {

        this.dtoEstudio = dtoEstudio;

    }

    @Override
    public void execute() throws Exception {

        GenericMapper estudioMapper = MapperFactory.estudioMapperInstancia();
        DaoEstudio daoEstudio = DaoFactory.DaoEstudioInstancia();

        this.dtoEstudio = (DtoEstudio) estudioMapper.CreateDto(daoEstudio.update((EstudioEntity) estudioMapper.CreateEntity(this.dtoEstudio)));

    }

    @Override
    public DtoResponse getResult() throws Exception {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Estudio actualizado");
        dtoResponse.setObjeto(this.dtoEstudio);

        return dtoResponse;
    }
}
