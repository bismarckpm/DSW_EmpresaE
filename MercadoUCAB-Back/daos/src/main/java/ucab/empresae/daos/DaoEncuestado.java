package ucab.empresae.daos;

import ucab.empresae.entidades.*;

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
            TypedQuery<EncuestadoEntity> encuestado = this._em.createNamedQuery("getEncuestadoByUsuario", EncuestadoEntity.class);
            encuestado.setParameter("usuario", usuarioEntity).getSingleResult();

            EncuestadoEntity resultado = encuestado.getSingleResult();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }

    public List<EncuestadoEntity> getDataMuestraEstudio(LugarEntity lugar, NivelSocioeconomicoEntity nivelSocioeconomico){

        try{
            TypedQuery<EncuestadoEntity> encuestados = this._em.createNamedQuery("getDataMuestraEstudio", EncuestadoEntity.class);
            encuestados.setParameter("lugar", lugar).setParameter("nivelSocioeconomico", nivelSocioeconomico).getResultList();

            List<EncuestadoEntity> resultado = encuestados.getResultList();
            return resultado;

        }catch (Exception e){
            return null;
        }
    }
}