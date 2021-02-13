package ucab.empresae.daos;

import ucab.empresae.entidades.OpcionEntity;
import ucab.empresae.entidades.RespuestaEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DaoRespuesta extends Dao<RespuestaEntity>{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoRespuesta() {
        super(_handler);
        this._em = _handler.getSession();
    }

    public Long getCantidadRespuestas(long id_estudio, OpcionEntity opcion){

        try{
            TypedQuery<Long> cantidadRespuestas = this._em.createQuery("select count(re) from RespuestaEntity re where re.estudio._id = :id_estudio and re.preguntaOpcion._id in (select preopc._id from PreguntaOpcionEntity preopc where preopc.opcion = :opcion)", Long.class);
            cantidadRespuestas.setParameter("id_estudio", id_estudio).setParameter("opcion", opcion).getSingleResult();

            Long resultado = cantidadRespuestas.getSingleResult();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }
}
