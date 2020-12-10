package ucab.empresae.daos;

import ucab.empresae.entidades.LugarEntity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoLugar extends Dao<LugarEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoLugar() {
        super(_handler);
        this._em = _handler.getSession();
    }


    public List<LugarEntity> getLugaresById(LugarEntity lugarEntity){

        try{
            TypedQuery<LugarEntity> lugares = this._em.createNamedQuery("getLugaresById", LugarEntity.class);
            lugares.setParameter("lugar", lugarEntity).getResultList();

            List<LugarEntity> resultado = lugares.getResultList();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }

    public List<LugarEntity> getLugaresByTipo(String tipo){

        try{
            TypedQuery<LugarEntity> lugares = this._em.createNamedQuery("getLugaresByTipo", LugarEntity.class);
            lugares.setParameter("tipo", tipo).getResultList();

            List<LugarEntity> resultado = lugares.getResultList();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }
}