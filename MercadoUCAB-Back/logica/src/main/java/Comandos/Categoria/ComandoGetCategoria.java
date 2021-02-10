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
 * Clase que se encarga de retornar una categoría especificada por su id
 * @author José Prieto
 */
public class ComandoGetCategoria extends ComandoBase<DtoResponse> {

    private final long id;
    private DtoBase dtoCategoria = DtoFactory.DtoCategoriaInstance();

    /**
     * Constructor de la clase.
     * @since 13/01/2021
     * @param id Objeto de tipo long que representa el id de la categoría
     * @throws CategoriaException en caso de que el id proporcionado no sea válido
     */
    public ComandoGetCategoria(long id) throws CategoriaException {
        if(id <= 0) {
            throw new CategoriaException("El id de la categoría debe ser mayor a 0.");
        } else {
            this.id = id;
        }
    }

    /**
     * Método que se encarga de buscar la categoría y luego la convierte a DtoCategoria
     * @since 13/01/2021
     * @throws Exception en caso de que el mapeo se haga de manera incorrecta.
     */
    @Override
    public void execute() throws Exception {
        Dao daoCategoria = DaoFactory.DaoCategoriaInstancia();
        BaseEntity categoria = (CategoriaEntity) daoCategoria.find(this.id, CategoriaEntity.class);

        if(categoria != null) {
            GenericMapper categoriaMapper = MapperFactory.categoriaMapperInstancia();
            this.dtoCategoria = (DtoBase) categoriaMapper.CreateDto(categoria);
        }
    }

    /**
     * Método encargado de retornar el resultado de la ejecución del código anterior en caso de que
     * @since 13/01/2021
     * @return Objeto de tipo DtoCategoria
     * @throws Exception Excepción lanzada por métodos anteriores.
     */
    @Override
    public DtoResponse getResult() throws Exception {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando Categoria");
        dtoResponse.setObjeto(this.dtoCategoria);

        return dtoResponse;
    }

}
