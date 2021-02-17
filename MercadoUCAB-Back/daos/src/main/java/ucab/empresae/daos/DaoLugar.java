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


    public List<LugarEntity> getLugaresById(long id){

        try{
            TypedQuery<LugarEntity> lugares = this._em.createQuery("select l from LugarEntity l where l.lugar._id = :id", LugarEntity.class);
            lugares.setParameter("id", id).getResultList();

            List<LugarEntity> resultado = lugares.getResultList();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }

    public List<LugarEntity> getLugaresByTipo(String tipo){

        try{
            TypedQuery<LugarEntity> lugares = this._em.createQuery("select l from LugarEntity  l where l.tipo = :tipo", LugarEntity.class);
            lugares.setParameter("tipo", tipo).getResultList();

            List<LugarEntity> resultado = lugares.getResultList();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }
}