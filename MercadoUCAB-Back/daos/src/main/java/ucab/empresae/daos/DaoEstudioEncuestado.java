package ucab.empresae.daos;

import ucab.empresae.entidades.EncuestadoEntity;
import ucab.empresae.entidades.EstudioEncuestadoEntity;
import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.entidades.OpcionEntity;

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
            TypedQuery<EstudioEncuestadoEntity> estudioEncuestado = this._em.createQuery("select estenc from EstudioEncuestadoEntity estenc where estenc.encuestado = :encuestado and estenc.estudio = :estudio", EstudioEncuestadoEntity.class);
            estudioEncuestado.setParameter("encuestado", encuestado).setParameter("estudio", estudio).getSingleResult();

            EstudioEncuestadoEntity resultado = estudioEncuestado.getSingleResult();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }

    public Long getCantidadEncuestados(long id_estudio){

        try{
            TypedQuery<Long> cantidadEncuestados = this._em.createQuery("select count(estenc) from EstudioEncuestadoEntity estenc where estenc.estudio._id = :id_estudio", Long.class);
            cantidadEncuestados.setParameter("id_estudio", id_estudio).getSingleResult();

            Long resultado = cantidadEncuestados.getSingleResult();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }

    public Long getCantidadEncuestadosFinalizado(long id_estudio){

        try{
            TypedQuery<Long> cantidadEncuestadosFinalizado = this._em.createQuery("select count(estenc) from EstudioEncuestadoEntity estenc where estenc.estudio._id = :id_estudio and estenc.estado = 'finalizado' ", Long.class);
            cantidadEncuestadosFinalizado.setParameter("id_estudio", id_estudio).getSingleResult();

            Long resultado = cantidadEncuestadosFinalizado.getSingleResult();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }

}
