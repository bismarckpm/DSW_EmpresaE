package Comandos.Pregunta;

import Comandos.ComandoBase;
import Mappers.MapperFactory;
import Mappers.Pregunta.PreguntaMapper;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoEstudio;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoPregunta;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.entidades.PreguntaEntity;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;
//extends ComandoBase<DtoResponse>
public class ComandoGetPreguntasBySubcategoria extends ComandoBase<DtoResponse>{
    private List<DtoPregunta> dtoPreguntas = new ArrayList<>();
    long id_estudio;
    /**
     * Constructor de la clase
     */
    public ComandoGetPreguntasBySubcategoria(long id) {
        this.id_estudio = id;
    }

    /**
     * Método encargado de la búsqueda de todas las preguntas.
     * @throws Exception En caso de algún problema a la hora de mapear de la lista de entidades a lista de dto
     */
    @Override
    public void execute() throws CustomException {
        DaoEstudio daoEstudio = DaoFactory.DaoEstudioInstancia();
        DaoPregunta daoPregunta = DaoFactory.DaoPreguntaInstancia();
        List<PreguntaEntity> preguntas = null;

        //Busco el estudio con el id de la url, y a su vez hago la busqueda de las preguntas que tienen esa subcategoria
        preguntas = daoPregunta.getPreguntasbySubcategoria(daoEstudio.find(id_estudio, EstudioEntity.class).getSubcategoria().get_id());

        PreguntaMapper preguntaMapper = MapperFactory.preguntaMapperInstancia();
        this.dtoPreguntas = preguntaMapper.CreateDtoList(preguntas);
    }

    /**
     * Método encargado de retornar las preguntas en forma de dtoPregunta
     * @return Lista de objetos de tipo DtoPregunta
     * @throws CustomException En caso de haber algún problema en métodos llamados con anterioridad
     */
    @Override
    public DtoResponse getResult() throws CustomException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando Preguntass");
        dtoResponse.setObjeto(this.dtoPreguntas);

        return dtoResponse;
    }
}
