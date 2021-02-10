package Comandos.Categoria;

import Comandos.ComandoBase;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.excepciones.CategoriaException;

/**
 * Clase encargada de eliminar una categoría del sistema
 * @author José Prieto
 */
public class ComandoDeleteCategoria extends ComandoBase<DtoResponse> {

    private final long id;

    /**
     * Constructor de la clase
     * @since 13/01/2021
     * @param id Objeto de tipo long con el id de la categoría a eliminar.
     */
    public ComandoDeleteCategoria(long id) {
        this.id = id;
    }

    /**
     * Método encargado de buscar la categoria para luego eliminarla.
     * @since 13/01/2021
     * @throws CategoriaException En caso de no encontrar la categoria especificada con el id.
     */
    @Override
    public void execute() throws CategoriaException {
        Dao daoCategoria = DaoFactory.DaoCategoriaInstancia();
        BaseEntity categoria = (CategoriaEntity) daoCategoria.find(this.id, CategoriaEntity.class);
        if (categoria.get_id() == 0) {
            throw new CategoriaException("Id introducido no coincide con ninguna categoría registrada en el sistema.");
        } else {
            daoCategoria.delete(categoria);
        }
    }

    /**
     * Método encargado de retornar visto bueno en caso de que la categoria se haya eliminado de manera exitosa.
     * @since 13/01/2021
     * @return Objeto de tipo string en caso de que la categoria se haya eliminado de la base de datos.
     * @throws CategoriaException arrastrada del método execute.
     */
    @Override
    public DtoResponse getResult() throws CategoriaException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Categoria eliminada");

        return dtoResponse;
    }
}
