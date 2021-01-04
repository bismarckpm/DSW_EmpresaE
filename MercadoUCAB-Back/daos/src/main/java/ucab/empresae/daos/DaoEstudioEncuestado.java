package ucab.empresae.daos;

import ucab.empresae.entidades.EncuestadoEntity;
import ucab.empresae.entidades.EstudioEncuestadoEntity;
import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.entidades.PreguntaOpcionEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DaoEstudioEncuestado extends Dao<EstudioEncuestadoEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudioEncuestado() {
        super(_handler);
        this._em = _handler.getSession();
    }

    public EstudioEncuestadoEntity getEstudioEncuestado(EncuestadoEntity encuestado, EstudioEntity estudio){

        try{
            TypedQuery<EstudioEncuestadoEntity> estudioEncuestado = this._em.createNamedQuery("getEstudioEncuestado", EstudioEncuestadoEntity.class);
            estudioEncuestado.setParameter("encuestado", encuestado).setParameter("estudio", estudio).getSingleResult();

            EstudioEncuestadoEntity resultado = estudioEncuestado.getSingleResult();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }

}
