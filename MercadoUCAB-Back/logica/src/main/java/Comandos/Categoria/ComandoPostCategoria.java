package Comandos.Categoria;

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
import ucab.empresae.excepciones.CategoriaException;

/**
 * Clase encargada de registrar una nueva categoria.
 * @author José Prieto
 */
public class ComandoPostCategoria extends ComandoBase<DtoResponse> {

    private DtoBase dtoCategoria;

    /**
     * Constructor de la clase
     * @since 13/01/221
     * @param dtoCategoria Objeto de tipo DtoCategoria con el que se obtienen los datos para la inserción.
     */
    public ComandoPostCategoria(DtoBase dtoCategoria) {
        this.dtoCategoria = dtoCategoria;
    }

    /**
     * Método encargado de la inserción en sí de la categoria.
     * @since 13/01/2021
     * @throws Exception En caso de que al categoria no se haya insertado de manera correcta.
     */
    @Override
    public void execute() throws Exception {
        Dao daoCategoria = DaoFactory.DaoCategoriaInstancia();
        GenericMapper categoriaMapper = MapperFactory.categoriaMapperInstancia();
        BaseEntity categoria = categoriaMapper.CreateEntity(this.dtoCategoria);
        categoria = (CategoriaEntity) daoCategoria.insert(categoria);

        if(categoria.get_id() == 0) {
            throw new CategoriaException("La categoría no se agregó de manera correcta.");
        } else {
            this.dtoCategoria = (DtoBase) categoriaMapper.CreateDto(categoria);
        }
    }

    /**
     * Método encargado de retornar la categoría recién insertada.
     * @since 13/01/2021
     * @return Objeto de tipo DtoCategoria.
     * @throws Exception Posibles errores arrastrados del método execute().
     */
    @Override
    public DtoResponse getResult() throws Exception {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Categoria insertada");
        dtoResponse.setObjeto(this.dtoCategoria);

        return dtoResponse;
    }

}
