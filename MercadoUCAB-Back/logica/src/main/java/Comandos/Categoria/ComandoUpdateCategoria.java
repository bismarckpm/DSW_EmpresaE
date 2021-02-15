package Comandos.Categoria;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoBase;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.excepciones.CategoriaException;

/**
 * Método encargado de hacer la actualización de la información de una categoría en específico.
 * @author José Prieto
 */
public class ComandoUpdateCategoria extends ComandoBase<DtoBase> {

    private DtoBase dtoCategoria;

    /**
     * Constructor de la clase
     * @since 13/01/2021
     * @param dtoCategoria Objeto de tipo DtoCategoria con la información relevante para proceder con la actualización.
     */
    public ComandoUpdateCategoria(DtoBase dtoCategoria) {
        this.dtoCategoria = dtoCategoria;
    }

    /**
     * Método encargado de hacer el update de la categoria especificada.
     * @since 13/01/2021
     * @throws Exception En caso de que la categoria no se haya actualizado de manera correcta.
     */
    @Override
    public void execute() throws Exception {
        Dao daoCategoria = DaoFactory.DaoCategoriaInstancia();
        GenericMapper categoriaMapper = MapperFactory.categoriaMapperInstancia();
        BaseEntity categoria = categoriaMapper.CreateEntity(this.dtoCategoria);
        categoria = (CategoriaEntity) daoCategoria.update(categoria);

        if(categoria.get_id() == 0) {
            throw new CategoriaException("La categoría no se actualizó de manera correcta.");
        } else {
            this.dtoCategoria = (DtoBase) categoriaMapper.CreateDto(categoria);
        }
    }

    /**
     * Método encargado de retornar la categoría con s información actualizada.
     * @since 13/01/2021
     * @return Objeto de tipo DtoCategoria en caso de haberse hecho la actualización de manera correcta.
     * @throws Exception Errores arrastrados del método execute().
     */
    @Override
    public DtoBase getResult() throws Exception {
        execute();
        return this.dtoCategoria;
    }

}
