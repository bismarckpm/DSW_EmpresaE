package Comandos.Estudio;

import Comandos.ComandoBase;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoOpcion;
import ucab.empresae.daos.DaoPregunta;
import ucab.empresae.daos.DaoRespuesta;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.OpcionAux;
import ucab.empresae.entidades.OpcionEntity;
import ucab.empresae.entidades.PreguntaAux;
import ucab.empresae.entidades.PreguntaEntity;

import java.util.ArrayList;
import java.util.List;

public class ComandoGetResultadoEstudio extends ComandoBase<DtoResponse> {

    private final long id;
    private List<PreguntaAux> preguntaAuxList = new ArrayList<>();

    public ComandoGetResultadoEstudio(long id) {

        this.id = id;

    }

    @Override
    public void execute() throws Exception {

        long contadorRespuestas;
        DaoOpcion daoOpcion = DaoFactory.DaoOpcionInstancia();

        DaoPregunta dao = DaoFactory.DaoPreguntaInstancia();
        DaoRespuesta daoRespuesta = DaoFactory.DaoRespuestaInstancia();
        List<PreguntaEntity> preguntas = dao.getPreguntasbyEstudio(this.id);

        for(PreguntaEntity pregunta : preguntas){
            PreguntaAux preguntaAux = new PreguntaAux(pregunta.get_id());
            List<OpcionEntity> opciones = daoOpcion.getOpciones(pregunta);
            List<OpcionAux> opcionAuxList = new ArrayList<>();

            preguntaAux.setDescripcion(pregunta.getDescripcion());
            preguntaAux.setTipo(pregunta.getTipo());

            for(OpcionEntity opcion : opciones){
                OpcionAux opcionAux = new OpcionAux(opcion.get_id());
                opcionAux.setDescripcion(opcion.getDescripcion());
                opcionAux.setEstado(opcion.getEstado());
                contadorRespuestas = daoRespuesta.getCantidadRespuestas(id, opcion);
                opcionAux.setValor(contadorRespuestas);

                opcionAuxList.add(opcionAux);
            }

            preguntaAux.setOpcionesResultado(opcionAuxList);

            this.preguntaAuxList.add(preguntaAux);
        }

    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando respuestas");
        dtoResponse.setObjeto(this.preguntaAuxList);

        return dtoResponse;

    }

}
