package Comandos.Pregunta;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import Mappers.Pregunta.PreguntaMapper;
import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoBase;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComandoUpdatePregunta extends ComandoBase<DtoResponse> {
    private DtoPregunta dtoPregunta;
    private long id;

    /**
     * Constructor de la clase
     * @param dtoPregunta Objeto de tipo DtoPregunta con la información relevante para proceder con la actualización.
     */
    public ComandoUpdatePregunta(long id, DtoPregunta dtoPregunta) {
        this.id = id;
        this.dtoPregunta = dtoPregunta;
    }



    /**
     * Metodo que recibe un objeto pregunta para borrar las opciones asociadas a esa pregunta
     * @param pregunta objeto pregunta que se utiliza para obtener las opciones asociadas
     */
    public void borrarOpciones(PreguntaEntity pregunta) {
        DaoOpcion daoOpcion = DaoFactory.DaoOpcionInstancia();

        List<OpcionEntity> opciones = daoOpcion.getOpciones(pregunta);

        for (OpcionEntity opcion : opciones) {
            OpcionEntity opcionEliminar = daoOpcion.find(opcion.get_id(), OpcionEntity.class);
            daoOpcion.delete(opcionEliminar);
        }
    }


    /**
     * Método encargado de hacer el update de la pregunta especificada.
     * @throws CustomException En caso de que la pregunta no se haya actualizado de manera correcta.
     */
    @Override
    public void execute() throws CustomException {
        PreguntaMapper preguntaMapper = MapperFactory.preguntaMapperInstancia();
        DaoPregunta daoPregunta = DaoFactory.DaoPreguntaInstancia();
        PreguntaEntity pregunta = daoPregunta.find(this.id, PreguntaEntity.class);

        DaoPreguntaOpcion daoPreguntaOpcion = DaoFactory.DaoPreguntaOpcionInstancia();
        DaoOpcion daoOpcion = DaoFactory.DaoOpcionInstancia();

        // SI LA PREGUNTA EXISTENTE TIENE OPCIONES ASOCIADAS, SE ELIMINAN.

        if (pregunta.getTipo().get_id() == 2 || pregunta.getTipo().get_id() == 3 || pregunta.getTipo().get_id() == 4 || pregunta.getTipo().get_id() == 5) {
            this.borrarOpciones(pregunta);
        }

        PreguntaEntity preguntaUpdate = preguntaMapper.CreateEntityUpdate(id, dtoPregunta);
        preguntaUpdate = daoPregunta.update(preguntaUpdate);


        // Tipo de Pregunta de Seleccion
        if (dtoPregunta.getTipo().get_id() == 3 || dtoPregunta.getTipo().get_id() == 4) {

            String[] opciones = dtoPregunta.getOpciones();
            int contador = 0;
            while (contador < opciones.length) {
                OpcionEntity opcion_entidad = EntidadesFactory.OpcionInstance();
                opcion_entidad.setDescripcion(opciones[contador]);
                opcion_entidad.setEstado("a");
                OpcionEntity resultadoOpcion = daoOpcion.insert(opcion_entidad);


                PreguntaOpcionEntity pregunta_opcion_nn = EntidadesFactory.PreguntaOpcionInstance();
                pregunta_opcion_nn.setEstado("a");
                pregunta_opcion_nn.setOpcion(resultadoOpcion);
                pregunta_opcion_nn.setPregunta(preguntaUpdate);
                daoPreguntaOpcion.insert(pregunta_opcion_nn);

                contador = contador + 1;
            }
        }

        // Tipo de pregunta de Verdadero y Falso
        if (dtoPregunta.getTipo().get_id() == 2) {   //Asigna en la n a n el verdadero o falso
            List<OpcionEntity> opciones = new ArrayList<>();
            opciones.add(new OpcionEntity("Verdadero", "a"));
            opciones.add(new OpcionEntity("Falso", "a"));

            for (OpcionEntity opcion : opciones) {
                daoOpcion.insert(opcion);

                PreguntaOpcionEntity pregunta_opcion_nn = EntidadesFactory.PreguntaOpcionInstance();
                pregunta_opcion_nn.setEstado("a");
                pregunta_opcion_nn.setOpcion(opcion);
                pregunta_opcion_nn.setPregunta(preguntaUpdate);
                daoPreguntaOpcion.insert(pregunta_opcion_nn);
            }
        }

        if (dtoPregunta.getTipo().get_id() == 5) {   //Asigna en la n a n EL rango
            List<OpcionEntity> opciones = new ArrayList<>();
            opciones.add(new OpcionEntity("1", "a"));
            opciones.add(new OpcionEntity("2", "a"));
            opciones.add(new OpcionEntity("3", "a"));
            opciones.add(new OpcionEntity("4", "a"));
            opciones.add(new OpcionEntity("5", "a"));

            for (OpcionEntity opcion : opciones) {
                daoOpcion.insert(opcion);

                PreguntaOpcionEntity pregunta_opcion_nn = EntidadesFactory.PreguntaOpcionInstance();
                pregunta_opcion_nn.setEstado("a");
                pregunta_opcion_nn.setOpcion(opcion);
                pregunta_opcion_nn.setPregunta(preguntaUpdate);
                daoPreguntaOpcion.insert(pregunta_opcion_nn);
            }
        }

        if(pregunta.get_id() == 0) {
            throw new CustomException("COMPREG003","La pregunta no se actualizo de manera correcta.");
        } else {
            this.dtoPregunta = preguntaMapper.CreateDto(preguntaUpdate);
        }

    }

    /**
     * Método encargado de retornar la pregunta con su información actualizada.
     * @return Objeto de tipo DtoPregunta en caso de haberse hecho la actualización de manera correcta.
     * @throws CustomException Errores arrastrados del método execute().
     */
    @Override
    public DtoResponse getResult() throws CustomException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("111");
        dtoResponse.setMensaje("Pregunta actualizada");
        dtoResponse.setObjeto(this.dtoPregunta);

        return dtoResponse;
    }
}
