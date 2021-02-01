package ucab.empresae.daos;

import ucab.empresae.entidades.EncuestaEntity;
import ucab.empresae.entidades.PreguntaEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Data access object de la entidad Encuesta
 */
public class DaoEncuesta extends Dao<EncuestaEntity>{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEncuesta()
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    /**
     * Metodo que permite obtener las encuestas
     * @return lista de encuestas
     */
    public List<EncuestaEntity> getEncuestas(){

        try{
            TypedQuery<EncuestaEntity> encuestas = this._em.createQuery("SELECT en from EncuestaEntity en where en.pregunta._id = (select MAX(en2.pregunta._id) from EncuestaEntity en2 where en2.estudio._id = en.estudio._id)", EncuestaEntity.class);
            encuestas.getResultList();

            List<EncuestaEntity> resultado = encuestas.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }

    /**
     * Metodo que permite obtener las preguntas asociadas a un estudio
     * @param id identificador del estudio para obtener las preguntas de ese estudio
     * @return lista de preguntas pertenecientes a la n a n encuesta
     */
    public List<EncuestaEntity> getPreguntasEncuesta(long id){

        try{
            TypedQuery<EncuestaEntity> encuestas = this._em.createQuery("select en from EncuestaEntity en where en.estudio._id = :id", EncuestaEntity.class);
            encuestas.setParameter("id", id).getResultList();

            List<EncuestaEntity> resultado = encuestas.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }
}
