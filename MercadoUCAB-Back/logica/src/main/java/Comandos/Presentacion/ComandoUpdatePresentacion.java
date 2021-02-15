package Comandos.Presentacion;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoBase;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.entidades.PresentacionEntity;
import ucab.empresae.excepciones.CategoriaException;
import ucab.empresae.excepciones.CustomException;

public class ComandoUpdatePresentacion extends ComandoBase<DtoResponse> {
    private DtoBase dtoPresentacion;

    /**
     * Constructor de la clase
     * @since 13/01/2021
     * @param dtoPresentacion Objeto de tipo DtoPresentacion con la información relevante para proceder con la actualización.
     */
    public ComandoUpdatePresentacion(DtoBase dtoPresentacion) {
        this.dtoPresentacion = dtoPresentacion;
    }

    /**
     * Método encargado de hacer el update de la presentacion especificada.
     * @throws CustomException En caso de que la presentacion no se haya actualizado de manera correcta.
     */
    @Override
    public void execute() throws CustomException {
        Dao daoPresentacion = DaoFactory.DaoPresentacionInstancia();
        GenericMapper presentacionMapper = MapperFactory.presentacionMapperInstancia();
        BaseEntity presentacion = presentacionMapper.CreateEntity(this.dtoPresentacion);
        presentacion = (PresentacionEntity) daoPresentacion.update(presentacion);

        if(presentacion.get_id() == 0) {
            throw new CustomException("COMPRE003","La presentacion no se actualizó de manera correcta.");
        } else {
            this.dtoPresentacion = (DtoBase) presentacionMapper.CreateDto(presentacion);
        }
    }

    /**
     * Método encargado de retornar la presentacion con su información actualizada.
     * @return Objeto de tipo DtoPresentacion en caso de haberse hecho la actualización de manera correcta.
     * @throws CustomException Errores arrastrados del método execute().
     */
    @Override
    public DtoResponse getResult() throws CustomException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Presentacion actualizada");
        dtoResponse.setObjeto(this.dtoPresentacion);

        return dtoResponse;
    }
}
