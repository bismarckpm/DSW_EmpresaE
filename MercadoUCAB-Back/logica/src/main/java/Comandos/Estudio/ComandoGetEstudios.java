package Comandos.Estudio;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.excepciones.CustomException;

import java.text.ParseException;
import java.util.List;

/**
 * Lógica para obtener todos los estudios en el sistema
 * @author José Prieto
 */
public class ComandoGetEstudios extends ComandoBase<DtoResponse> {

    private List<DtoEstudio> dtoEstudios;

    public ComandoGetEstudios() {

    }

    @Override
    public void execute() throws CustomException, ParseException {
        Dao daoEstudio = DaoFactory.DaoEstudioInstancia();
        List<BaseEntity> estudios = daoEstudio.findAll(EstudioEntity.class);

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
