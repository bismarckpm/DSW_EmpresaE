package ucab.empresae.daos;

import ucab.empresae.entidades.ClienteEntity;
import ucab.empresae.entidades.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DaoCliente extends Dao<ClienteEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoCliente( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    public ClienteEntity getClienteByUsuario(UsuarioEntity usuarioEntity){

        try{
            TypedQuery<ClienteEntity> cliente = this._em.createNamedQuery("getClienteByUsuario", ClienteEntity.class);
            cliente.setParameter("usuario", usuarioEntity).getSingleResult();

            ClienteEntity resultado = cliente.getSingleResult();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }
}
