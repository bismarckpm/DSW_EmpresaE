package Comandos.Presentacion;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoPresentacion;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.entidades.PresentacionEntity;
import ucab.empresae.excepciones.CategoriaException;
import ucab.empresae.excepciones.CustomException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ComandoGetPresentaciones extends ComandoBase<DtoResponse> {

    private List<DtoPresentacion> dtoPresentaciones = new ArrayList<>();

    /**
     * Constructor de la clase
     */
    public ComandoGetPresentaciones() {
    }

    /**
     * Método encargado de la búsqueda de todas las presentaciones.
     * @throws CustomException En caso de algún problema a la hora de mapear de la lista de entidades a lista de dto
     */
    @Override
    public void execute() throws CustomException, ParseException {
        Dao daoPresentacion = DaoFactory.DaoPresentacionInstancia();
        List<BaseEntity> presentaciones = daoPresentacion.findAll(PresentacionEntity.class);

        GenericMapper presentacionMapper = MapperFactory.presentacionMapperInstancia();
        this.dtoPresentaciones = presentacionMapper.CreateDtoList(presentaciones);
    }

    /**
     * Método encargado de retornar las categorías en forma de dtoCategoria
     * @return Lista de objetos de tipo DtoPresentacion
     * @throws CustomException En caso de haber algún problema en métodos llamados con anterioridad
     */
    @Override
    public DtoResponse getResult() throws CustomException, ParseException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando Presentaciones");
        dtoResponse.setObjeto(this.dtoPresentaciones);

        return dtoResponse;
    }
}
