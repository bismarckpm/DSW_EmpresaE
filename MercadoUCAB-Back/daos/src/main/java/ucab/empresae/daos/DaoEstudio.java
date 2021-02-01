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
                                    .createQuery("SELECT e FROM EstudioEntity e where e in (select c.estudio from ClienteEstudioEntity c where c.cliente.usuario._id = :id)", EstudioEntity.class);
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
                    .createQuery("SELECT e FROM EstudioEntity e where e.analista._id = :id", EstudioEntity.class);
            estudios.setParameter("id", id).getResultList();

            List<EstudioEntity> resultado = estudios.getResultList();

            return resultado;
        }catch (Exception e){
            return null;
        }
    }


    public List<EstudioEntity> getEstudios(LugarEntity lugar, NivelSocioeconomicoEntity nivelSocioeconomico){

        try{
            TypedQuery<EstudioEntity> estudios = this._em.createQuery("select es from EstudioEntity es where es.lugar = :lugar and es.nivelSocioEconomico = :nivelSocioeconomico", EstudioEntity.class);
            estudios.setParameter("lugar", lugar).setParameter("nivelSocioeconomico", nivelSocioeconomico).getResultList();

            List<EstudioEntity> resultado = estudios.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }

    public List<EstudioEntity> getEstudiosEncuestado(EncuestadoEntity encuestadoEntity){

        try{
            TypedQuery<EstudioEntity> estudios = this._em.createQuery("select es from EstudioEntity es where es._id in (select estenc.estudio._id from EstudioEncuestadoEntity estenc where estenc.encuestado = :encuestado) and es.estado != 'Culminado'", EstudioEntity.class);
            estudios.setParameter("encuestado", encuestadoEntity).getResultList();

            List<EstudioEntity> resultado = estudios.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }

    public List<EstudioEntity> getEstudiosSinEncuesta(){

        try{
            TypedQuery<EstudioEntity> estudios = this._em.createQuery("select es from EstudioEntity es where es._id not in (select enc.estudio._id from EncuestaEntity enc)", EstudioEntity.class);
            estudios.getResultList();

            List<EstudioEntity> resultado = estudios.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }
}
