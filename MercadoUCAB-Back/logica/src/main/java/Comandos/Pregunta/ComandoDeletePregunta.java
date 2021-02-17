package Comandos.Pregunta;

import Comandos.ComandoBase;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoPregunta;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.PreguntaEntity;
import ucab.empresae.excepciones.CustomException;

public class ComandoDeletePregunta extends ComandoBase<DtoResponse> {
    private final long id;

    /**
     * Constructor de la clase
     * @param id Objeto de tipo long con el id de la pregunta a eliminar.
     */
    public ComandoDeletePregunta(long id) {
        this.id = id;
    }

    /**
     * Método encargado de buscar la pregunta para luego eliminarla.
     * @throws CustomException En caso de no encontrar la pregunta especificada con el id.
     */
    @Override
    public void execute() throws CustomException {
        DaoPregunta daoPregunta = DaoFactory.DaoPreguntaInstancia();
        PreguntaEntity pregunta = daoPregunta.find(this.id, PreguntaEntity.class);
        if (pregunta.get_id() == 0) {
            throw new CustomException("COMPREG002","Id introducido no coincide con ninguna pregunta registrada en el sistema.");
        } else {
            daoPregunta.delete(pregunta);
        }
    }

    /**
     * Método encargado de retornar pregunta eliminada en caso de que la pregunta se haya eliminado de manera exitosa.
     * @return Objeto de tipo string en caso de que la presentacion se haya eliminado de la base de datos.
     * @throws CustomException arrastrada del método execute.
     */
    @Override
    public DtoResponse getResult() throws CustomException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("111");
        dtoResponse.setMensaje("Pregunta Eliminada");

        return dtoResponse;
    }
}
