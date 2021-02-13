package Comandos.Pregunta;

import Mappers.MapperFactory;
import Mappers.Pregunta.PreguntaMapper;
import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class ComandoGetEncuestaAnalista {
    private List<PreguntaAux> dtopreguntaAuxList = new ArrayList<PreguntaAux>();
    private List<DtoPregunta> dtoPreguntas = new ArrayList<>();
    long id_estudio;
    long id_encuestado;
    /**
     * Constructor de la clase
     */
    public ComandoGetEncuestaAnalista(long id_estudio, long id_encuestado) {
        this.id_estudio = id_estudio;
        this.id_encuestado = id_encuestado;
    }

    /**
     * Método encargado de la búsqueda de todas las preguntas.
     * @throws Exception En caso de algún problema a la hora de mapear de la lista de entidades a lista de dto
     */
    //@Override
    public void execute() throws CustomException {
        List<PreguntaEntity> preguntas = null;
        DaoOpcion daoOpcion = DaoFactory.DaoOpcionInstancia();

        DaoPregunta dao = DaoFactory.DaoPreguntaInstancia();
        preguntas = dao.getPreguntasbyEstudioEncuestado(id_estudio, id_encuestado);

        for(PreguntaEntity pregunta : preguntas){
            PreguntaAux dtopreguntaAux = new PreguntaAux(pregunta.get_id());
            List<OpcionEntity> opciones = daoOpcion.getOpciones(pregunta);

            dtopreguntaAux.setDescripcion(pregunta.getDescripcion());
            dtopreguntaAux.setTipo(pregunta.getTipo());
            dtopreguntaAux.setOpciones(opciones);

            this.dtopreguntaAuxList.add(dtopreguntaAux);
        }

        PreguntaMapper preguntaMapper = MapperFactory.preguntaMapperInstancia();
        this.dtoPreguntas = preguntaMapper.CreateDtoList(preguntas);
    }

    /**
     * Método encargado de retornar las preguntas en forma de dtoPregunta
     * @return Lista de objetos de tipo DtoPreguntaAux list
     * @throws CustomException En caso de haber algún problema en métodos llamados con anterioridad
     */
    //@Override
    public List<PreguntaAux> getResult() throws CustomException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando Encuesta");
        dtoResponse.setObjeto(this.dtopreguntaAuxList);

        return this.dtopreguntaAuxList;
    }
}
