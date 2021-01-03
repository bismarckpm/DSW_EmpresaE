package ucab.empresae.daos;

import ucab.empresae.entidades.OpcionEntity;
import ucab.empresae.entidades.PreguntaOpcionEntity;
import ucab.empresae.entidades.RespuestaEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoRespuesta extends Dao<RespuestaEntity>{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoRespuesta() {
        super(_handler);
        this._em = _handler.getSession();
    }

    public Long getCantidadRespuestas(long id_estudio, OpcionEntity opcion){

        try{
            TypedQuery<Long> cantidadRespuestas = this._em.createNamedQuery("getCantidadRespuestas", Long.class);
            cantidadRespuestas.setParameter("id_estudio", id_estudio).setParameter("opcion", opcion).getSingleResult();

            Long resultado = cantidadRespuestas.getSingleResult();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }
}
