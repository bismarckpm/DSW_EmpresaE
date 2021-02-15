package Comandos.Presentacion;

import Comandos.ComandoBase;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.PresentacionEntity;
import ucab.empresae.excepciones.CustomException;

public class ComandoDeletePresentacion extends ComandoBase<DtoResponse> {
    private final long id;

    /**
     * Constructor de la clase
     * @param id Objeto de tipo long con el id de la presentacion a eliminar.
     */
    public ComandoDeletePresentacion(long id) {
        this.id = id;
    }

    /**
     * Método encargado de buscar la presentacion para luego eliminarla.
     * @throws CustomException En caso de no encontrar la presentacion especificada con el id.
     */
    @Override
    public void execute() throws CustomException {
        Dao daoPresentacion = DaoFactory.DaoPresentacionInstancia();
        BaseEntity presentacion = (PresentacionEntity) daoPresentacion.find(this.id, PresentacionEntity.class);
        if (presentacion.get_id() == 0) {
            throw new CustomException("COMPRE002","Id introducido no coincide con ninguna presentacion registrada en el sistema.");
        } else {
            daoPresentacion.delete(presentacion);
        }
    }

    /**
     * Método encargado de retornar presentacion eliminada en caso de que la presentacion se haya eliminado de manera exitosa.
     * @return Objeto de tipo string en caso de que la presentacion se haya eliminado de la base de datos.
     * @throws CustomException arrastrada del método execute.
     */
    @Override
    public DtoResponse getResult() throws CustomException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Presentacion Eliminada");

        return dtoResponse;
    }
}
