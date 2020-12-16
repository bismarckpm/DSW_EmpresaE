package ucab.empresae.daos;

import ucab.empresae.entidades.EncuestaEntity;
import ucab.empresae.entidades.PreguntaEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoEncuesta extends Dao<EncuestaEntity>{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEncuesta()
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    public List<EncuestaEntity> getEncuestas(){

        try{
            TypedQuery<EncuestaEntity> encuestas = this._em.createNamedQuery("getEncuestas", EncuestaEntity.class);
            encuestas.getResultList();

            List<EncuestaEntity> resultado = encuestas.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }
}
