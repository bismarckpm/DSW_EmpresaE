package ucab.empresae.daos;

import ucab.empresae.entidades.EncuestadoEntity;
import ucab.empresae.entidades.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
}