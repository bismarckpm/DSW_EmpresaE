package ucab.empresae.daos;

import ucab.empresae.entidades.OpcionEntity;
import ucab.empresae.entidades.PreguntaEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoOpcion extends Dao<OpcionEntity>{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoOpcion() {
        super(_handler);
        this._em = _handler.getSession();
    }

    public List<OpcionEntity> getOpciones(PreguntaEntity pregunta){
        try {
            TypedQuery<OpcionEntity> opciones = this._em.createNamedQuery("getOpciones", OpcionEntity.class);
            opciones.setParameter("pregunta", pregunta).getResultList();

            List<OpcionEntity> resultado = opciones.getResultList();
            return resultado;
        }
        catch (Exception ex){
            return null;
        }
    }


}
