package Comandos.Pregunta;

import Comandos.ComandoBase;
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
//extends ComandoBase<DtoResponse>
public class ComandoGetEncuesta extends ComandoBase<DtoResponse>{
    private List<PreguntaAux> dtopreguntaAuxList = new ArrayList<PreguntaAux>();
    private List<DtoPregunta> dtoPreguntas = new ArrayList<>();
    long id_estudio;
    long id_encuestado;
    /**
     * Constructor de la clase
     */
    public ComandoGetEncuesta(long id_estudio, long id_encuestado) {
        this.id_estudio = id_estudio;
        this.id_encuestado = id_encuestado;
    }

    /**
     * Método encargado de la búsqueda de todas las preguntas.
     * @throws Exception En caso de algún problema a la hora de mapear de la lista de entidades a lista de dto
     */
    @Override
    public void execute() throws CustomException {
        DaoUsuario daoUsuario = DaoFactory.DaoUsuarioInstancia();
        DaoEncuestado daoEncuestado = DaoFactory.DaoEncuestadoInstancia();
        UsuarioEntity usuarioEntity = daoUsuario.find(id_encuestado, UsuarioEntity.class);
        EncuestadoEntity encuestadoEntity = daoEncuestado.getEncuestadoByUsuario(usuarioEntity);

        List<PreguntaEntity> preguntas = null;
        DaoOpcion daoOpcion = DaoFactory.DaoOpcionInstancia();

        DaoPregunta dao = DaoFactory.DaoPreguntaInstancia();
        preguntas = dao.getPreguntasbyEstudioEncuestado(id_estudio, encuestadoEntity.get_id());

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
    @Override
    public DtoResponse getResult() throws CustomException {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando Encuesta");
        dtoResponse.setObjeto(this.dtopreguntaAuxList);

        return dtoResponse;
    }
}
