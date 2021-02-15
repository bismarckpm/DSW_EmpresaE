package ucab.empresae.daos;

import ucab.empresae.entidades.EncuestadoEntity;
import ucab.empresae.entidades.LugarEntity;
import ucab.empresae.entidades.NivelSocioeconomicoEntity;
import ucab.empresae.entidades.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoEncuestado extends Dao<EncuestadoEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEncuestado( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    public EncuestadoEntity getEncuestadoByUsuario(UsuarioEntity usuarioEntity){

        try{
            TypedQuery<EncuestadoEntity> encuestado = this._em.createQuery("select e from EncuestadoEntity e where e.usuario = :usuario", EncuestadoEntity.class);
            encuestado.setParameter("usuario", usuarioEntity).getSingleResult();

            EncuestadoEntity resultado = encuestado.getSingleResult();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }

    public List<EncuestadoEntity> getDataMuestraEstudio(LugarEntity lugar, NivelSocioeconomicoEntity nivelSocioeconomico){

        try{
            TypedQuery<EncuestadoEntity> encuestados = this._em.createQuery("select enc from EncuestadoEntity enc where enc.lugar = :lugar and enc.nivelsocioeco = :nivelSocioeconomico", EncuestadoEntity.class);
            encuestados.setParameter("lugar", lugar).setParameter("nivelSocioeconomico", nivelSocioeconomico).getResultList();

            List<EncuestadoEntity> resultado = encuestados.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }

    public List<EncuestadoEntity> getDataMuestraEstudioxAnalista(long id_estudio){

        try{
            TypedQuery<EncuestadoEntity> encuestados = this._em.createQuery("select enc from EncuestadoEntity enc where enc._id in (select esenc.encuestado._id from EstudioEncuestadoEntity esenc where esenc.estudio._id = :id_estudio)", EncuestadoEntity.class);
            encuestados.setParameter("id_estudio", id_estudio).getResultList();

            List<EncuestadoEntity> resultado = encuestados.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }
}