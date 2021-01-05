package ucab.empresae.daos;

import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.entidades.LugarEntity;
import ucab.empresae.entidades.PreguntaEntity;
import ucab.empresae.entidades.SubcategoriaEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Data access object de la entidad Pregunta
 */
public class DaoPregunta extends Dao<PreguntaEntity>
{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPregunta() {
        super(_handler);
        this._em = _handler.getSession();
    }

    /**
     * Metodo que permite filtrar las preguntas por subcategoria
     * @param id identificador de la subcategoria
     * @return lista de preguntas pertenecientes a una subcategoria
     */
    public List<PreguntaEntity> getPreguntasbySubcategoria(long id){

        try{
            TypedQuery<PreguntaEntity> preguntas = this._em.createNamedQuery("getPreguntasbySubcategoria", PreguntaEntity.class);
            preguntas.setParameter("id", id).getResultList();

            List<PreguntaEntity> resultado = preguntas.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }

    /**
     * Metodo que permite filtrar las preguntas por estudio
     * @param id identificador del estudio
     * @return lista de preguntas pertenecientes a un estudio
     */
    public List<PreguntaEntity> getPreguntasbyEstudio(long id){

        try{
            TypedQuery<PreguntaEntity> preguntas = this._em.createNamedQuery("getPreguntasbyEstudio", PreguntaEntity.class);
            preguntas.setParameter("id", id).getResultList();

            List<PreguntaEntity> resultado = preguntas.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }
}
