package ucab.empresae.daos;

import ucab.empresae.entidades.TipoUsuarioEntity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DaoTipoUsuario extends Dao<TipoUsuarioEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoTipoUsuario( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    public TipoUsuarioEntity getTipoUsuarioByDescripcion(String descripcion){

        try{
            TypedQuery<TipoUsuarioEntity> tipoUsuario = this._em.createNamedQuery("getTipoUsuarioByDescripcion", TipoUsuarioEntity.class);
            tipoUsuario.setParameter("descripcion", descripcion).getSingleResult();

            TipoUsuarioEntity resultado = tipoUsuario.getSingleResult();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }
}
