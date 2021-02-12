package Comandos.Pregunta;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import Mappers.Pregunta.PreguntaMapper;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoBase;
import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de registrar una nueva pregunta.
 */
public class ComandoPostPregunta extends ComandoBase<DtoResponse> {
    private DtoPregunta dtoPregunta;

    /**
     * Constructor de la clase
     * @param dtoPregunta Objeto de tipo DtoPregunta con el que se obtienen los datos para la inserción.
     */
    public ComandoPostPregunta(DtoPregunta dtoPregunta) {
        this.dtoPregunta = dtoPregunta;
    }

    /**
     * Método encargado de la inserción de la pregunta.
     * @throws CustomException En caso de que la Pregunta no se haya insertado de manera correcta.
     */
    @Override
    public void execute() throws CustomException {

        DaoPregunta daoPregunta = DaoFactory.DaoPreguntaInstancia();
        DaoTipoPregunta daoTipoPregunta = DaoFactory.DaoTipoPreguntaInstancia();
        DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();
        DaoOpcion daoOpcion = DaoFactory.DaoOpcionInstancia();
        DaoPreguntaOpcion daoPreguntaOpcion = DaoFactory.DaoPreguntaOpcionInstancia();

        PreguntaMapper preguntaMapper = MapperFactory.preguntaMapperInstancia();

        SubcategoriaEntity subcategoria = daoSubcategoria.find(dtoPregunta.getSubcategoria().get_id(), SubcategoriaEntity.class);
        TipoPreguntaEntity tipoPregunta = daoTipoPregunta.find(dtoPregunta.getTipo().get_id(), TipoPreguntaEntity.class);

        PreguntaEntity pregunta = preguntaMapper.CreateEntity(this.dtoPregunta);
        pregunta = daoPregunta.insert(pregunta);

        // Tipo de Pregunta de Seleccion Simple o Seleccion multiple
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
                pregunta_opcion_nn.setPregunta(pregunta);
                daoPreguntaOpcion.insert(pregunta_opcion_nn);

                contador++;
            }
        }

        // Tipo de pregunta de Verdadero y Falso
        if (dtoPregunta.getTipo().get_id() == 2) {   //Asigna en la n a n el verdadero o falso
            List<OpcionEntity> opciones = new ArrayList<>();
            opciones.add(new OpcionEntity("Verdadero", "a"));
            opciones.add(new OpcionEntity("Falso", "a"));

            for (OpcionEntity opcion : opciones) {
                daoOpcion.insert(opcion);

                PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                pregunta_opcion_nn.setEstado("a");
                pregunta_opcion_nn.setOpcion(opcion);
                pregunta_opcion_nn.setPregunta(pregunta);
                daoPreguntaOpcion.insert(pregunta_opcion_nn);
            }
        }

        // Tipo de pregunta de Rango
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
                pregunta_opcion_nn.setPregunta(pregunta);
                daoPreguntaOpcion.insert(pregunta_opcion_nn);
            }
        }

        if(pregunta.get_id() == 0) {
            throw new CustomException("COMPRE001","La pregunta no se agregó de manera correcta.");
        } else {
            this.dtoPregunta = preguntaMapper.CreateDto(pregunta);
        }
    }

    /**
     * Método encargado de retornar la pregunta recién insertada.
     * @return Objeto de tipo DtoPregunta.
     * @throws CustomException Posibles errores arrastrados del método execute().
     */
    @Override
    public DtoResponse getResult() throws CustomException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("111");
        dtoResponse.setMensaje("Pregunta agregada satisfactoriamente");
        dtoResponse.setObjeto(this.dtoPregunta);

        return dtoResponse;
    }

}
