package ucab.empresae.daos;

import ucab.empresae.entidades.ClienteEntity;
import ucab.empresae.entidades.EncuestadoEntity;
import ucab.empresae.entidades.TelefonoEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DaoTelefono extends Dao<TelefonoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoTelefono( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    public TelefonoEntity getTelefonoByCliente(ClienteEntity clienteEntity){

        try{
            TypedQuery<TelefonoEntity> telefono = this._em.createNamedQuery("getTelefonoByCliente", TelefonoEntity.class);
            telefono.setParameter("cliente", clienteEntity).getSingleResult();

            TelefonoEntity resultado = telefono.getSingleResult();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }

    public TelefonoEntity getTelefonoByEncuestado(EncuestadoEntity encuestadoEntity){

        try{
            TypedQuery<TelefonoEntity> telefono = this._em.createNamedQuery("getTelefonoByEncuestado", TelefonoEntity.class);
            telefono.setParameter("encuestado", encuestadoEntity).getSingleResult();

            TelefonoEntity resultado = telefono.getSingleResult();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }
}
