package Comandos.Categoria;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.excepciones.CategoriaException;
import ucab.empresae.excepciones.CustomException;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de retornar la lista de todas las categorías registradas en el sistema.
 * @author José Prieto
 */
public class ComandoGetCategorias extends ComandoBase<DtoResponse> {

    private List<DtoCategoria> dtoCategorias = new ArrayList<>();

    /**
     * Constructor de la clase
     * @since 11/01/2021
     */
    public ComandoGetCategorias() {
    }

    /**
     * Método encargado de la búsqueda de todas las categorías.
     * @since 11/01/2021
     * @throws Exception En caso de algún problema a la hora de mapear de la lista de entidades a lista de dto
     */
    @Override
    public void execute() throws CustomException {
        Dao daoCategoria = DaoFactory.DaoCategoriaInstancia();
        List<BaseEntity> categorias = daoCategoria.findAll(CategoriaEntity.class);

        GenericMapper categoriaMapper = MapperFactory.categoriaMapperInstancia();
        this.dtoCategorias = categoriaMapper.CreateDtoList(categorias);

    }

    /**
     * Método encargado de retornar las categorías en forma de dtoCategoria
     * @since 11/01/2021
     * @return Lista de objetos de tipo DtoCategoria
     * @throws Exception En caso de haber algún problema en métodos llamados con anterioridad
     */
    @Override
    public DtoResponse getResult() throws Exception {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando Categorias");
        dtoResponse.setObjeto(this.dtoCategorias);

        return dtoResponse;
    }
}
