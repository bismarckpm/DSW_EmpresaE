package ucab.empresae.daos;

import ucab.empresae.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoEstudio extends Dao<EstudioEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudio() {
        super(_handler);
        this._em = _handler.getSession();
    }

    public List<EstudioEntity> estudiosCliente(long id) {
        try{
            TypedQuery<EstudioEntity> estudios = this._em
                                    .createNamedQuery("getEstudiosCliente", EstudioEntity.class);
            estudios.setParameter("id", id).getResultList();

            List<EstudioEntity> resultado = estudios.getResultList();

            return resultado;
        }catch (Exception e){
            return null;
        }
    }

    public List<EstudioEntity> estudiosAnalista(long id) {
        try{
            TypedQuery<EstudioEntity> estudios = this._em
                    .createNamedQuery("getEstudiosAnalista", EstudioEntity.class);
            estudios.setParameter("id", id).getResultList();

            List<EstudioEntity> resultado = estudios.getResultList();

            return resultado;
        }catch (Exception e){
            return null;
        }
    }


    public List<EstudioEntity> getEstudios(LugarEntity lugar, NivelSocioeconomicoEntity nivelSocioeconomico){

        try{
            TypedQuery<EstudioEntity> estudios = this._em.createNamedQuery("getEstudios", EstudioEntity.class);
            estudios.setParameter("lugar", lugar).setParameter("nivelSocioeconomico", nivelSocioeconomico).getResultList();

            List<EstudioEntity> resultado = estudios.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }

    public List<EstudioEntity> getEstudiosEncuestado(EncuestadoEntity encuestadoEntity){

        try{
            TypedQuery<EstudioEntity> estudios = this._em.createNamedQuery("getEstudiosEncuestado", EstudioEntity.class);
            estudios.setParameter("encuestado", encuestadoEntity).getResultList();

            List<EstudioEntity> resultado = estudios.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }


}
