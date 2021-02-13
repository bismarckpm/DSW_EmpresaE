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
import ucab.empresae.entidades.PresentacionEntity;
import ucab.empresae.excepciones.CustomException;

import java.text.ParseException;

/**
 * Clase encargada de registrar una nueva presentacion.
 */
public class ComandoPostPresentacion extends ComandoBase<DtoResponse> {
    private DtoBase dtoPresentacion;

    /**
     * Constructor de la clase
     * @param dtoPresentacion Objeto de tipo DtoPresentacion con el que se obtienen los datos para la inserción.
     */
    public ComandoPostPresentacion(DtoBase dtoPresentacion) {
            this.dtoPresentacion = dtoPresentacion;
    }

    /**
     * Método encargado de la inserción de la presentacion.
     * @throws CustomException En caso de que al categoria no se haya insertado de manera correcta.
     */
    @Override
    public void execute() throws CustomException, ParseException {
            Dao daoPresentacion = DaoFactory.DaoPresentacionInstancia();
            GenericMapper presentacionMapper = MapperFactory.presentacionMapperInstancia();
            BaseEntity presentacion = presentacionMapper.CreateEntity(this.dtoPresentacion);
            presentacion = (PresentacionEntity) daoPresentacion.insert(presentacion);

            if(presentacion.get_id() == 0) {
                throw new CustomException("COMPRE001","La presentacion no se agregó de manera correcta.");
            } else {
                this.dtoPresentacion = (DtoBase) presentacionMapper.CreateDto(presentacion);
            }
    }

    /**
     * Método encargado de retornar la categoría recién insertada.
     * @since 13/01/2021
     * @return Objeto de tipo DtoCategoria.
     * @throws CustomException Posibles errores arrastrados del método execute().
     */
    @Override
    public DtoResponse getResult() throws CustomException, ParseException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Agregando presentacion");
        dtoResponse.setObjeto(this.dtoPresentacion);

        return dtoResponse;
    }

}
