package Comandos.Pregunta;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import Mappers.Pregunta.PreguntaMapper;
import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.dtos.DtoPresentacion;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.PreguntaEntity;
import ucab.empresae.entidades.PresentacionEntity;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class ComandoGetPreguntas extends ComandoBase<DtoResponse> {
    private List<DtoPregunta> dtoPreguntas = new ArrayList<>();

    /**
     * Constructor de la clase
     */
    public ComandoGetPreguntas() {
    }

    /**
     * Método encargado de la búsqueda de todas las preguntas.
     * @throws Exception En caso de algún problema a la hora de mapear de la lista de entidades a lista de dto
     */
    @Override
    public void execute() throws CustomException {
        Dao daoPregunta = DaoFactory.DaoPreguntaInstancia();
        List<BaseEntity> preguntas = daoPregunta.findAll(PreguntaEntity.class);

        PreguntaMapper preguntaMapper = MapperFactory.preguntaMapperInstancia();
        this.dtoPreguntas = preguntaMapper.CreateDtoList(preguntas);
    }

    /**
     * Método encargado de retornar las categorías en forma de dtoPregunta
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
