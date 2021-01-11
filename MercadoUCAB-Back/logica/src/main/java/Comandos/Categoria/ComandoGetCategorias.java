package Comandos.Categoria;

import Comandos.ComandoBase;
import Mappers.Categoria.CategoriaMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.excepciones.CategoriaException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author José Prieto
 */
public class ComandoGetCategorias extends ComandoBase<List<DtoCategoria>> {

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
    public void execute() throws Exception {
        Dao daoCategoria = DaoFactory.DaoCategoriaInstancia();
        List<BaseEntity> categorias = daoCategoria.findAll(CategoriaEntity.class);

        if(!categorias.isEmpty()) {
            CategoriaMapper categoriaMapper = MapperFactory.categoriaMapperInstancia();
            this.dtoCategorias = categoriaMapper.CreateDtoList(categorias);
        } else{
            throw new CategoriaException("categorias esta vacio.");
        }
    }

    /**
     * Método encargado de retornar las categorías en forma de dtoCategoria
     * @since 11/01/2021
     * @return Lista de objetos de tipo DtoCategoria
     * @throws Exception En caso de haber algún problema en métodos llamados con anterioridad
     */
    @Override
    public List<DtoCategoria> getResult() throws Exception {
        execute();
        return this.dtoCategorias;
    }
}
