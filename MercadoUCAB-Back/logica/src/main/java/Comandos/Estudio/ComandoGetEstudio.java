package Comandos.Estudio;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.DaoEstudio;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.excepciones.CustomException;

public class ComandoGetEstudio extends ComandoBase<DtoResponse> {

    private DtoEstudio dtoEstudio;
    private final long id;

    public ComandoGetEstudio(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws CustomException {

        DaoEstudio daoEstudio = DaoFactory.DaoEstudioInstancia();
        BaseEntity estudio = daoEstudio.find(this.id, EstudioEntity.class);
        if (estudio == null) {
            throw new CustomException("COMEST001", "Id introducido no coincide con ningún estudio registrado en el sistema.");
        }else {
            GenericMapper estudioMapper = MapperFactory.estudioMapperInstancia();
            this.dtoEstudio =(DtoEstudio) estudioMapper.CreateDto(estudio);
        }

    }

    @Override
    public DtoResponse getResult() throws CustomException {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando estudio");
        dtoResponse.setObjeto(this.dtoEstudio);

        return dtoResponse;

    }

}
