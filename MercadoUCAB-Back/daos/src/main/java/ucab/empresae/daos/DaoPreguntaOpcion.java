package ucab.empresae.daos;

import ucab.empresae.entidades.PreguntaOpcionEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DaoPreguntaOpcion extends Dao<PreguntaOpcionEntity>{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPreguntaOpcion() {
        super(_handler);
        this._em = _handler.getSession();
    }

    public PreguntaOpcionEntity getPreguntaOpcion(long id_pregunta, long id_opcion){

        try{
            TypedQuery<PreguntaOpcionEntity> preguntaOpcion = this._em.createQuery("select pregopc from PreguntaOpcionEntity pregopc where pregopc.pregunta._id = :id_pregunta and pregopc.opcion._id = :id_opcion", PreguntaOpcionEntity.class);
            preguntaOpcion.setParameter("id_pregunta", id_pregunta).setParameter("id_opcion", id_opcion).getSingleResult();

            PreguntaOpcionEntity resultado = preguntaOpcion.getSingleResult();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }
}
