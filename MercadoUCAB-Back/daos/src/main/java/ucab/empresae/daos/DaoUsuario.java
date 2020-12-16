package ucab.empresae.daos;

import ucab.empresae.entidades.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoUsuario extends Dao<UsuarioEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoUsuario() {
        super(_handler);
        this._em = _handler.getSession();
    }

    public UsuarioEntity getUsuarioByUsername(String username){

        try{
            TypedQuery<UsuarioEntity> usuario = this._em.createNamedQuery("getUsuarioByUsername", UsuarioEntity.class);
            usuario.setParameter("username", username).getSingleResult();

            UsuarioEntity resultado = usuario.getSingleResult();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }

    public List<UsuarioEntity> getUsuariosEmpleados(){

        try{
            TypedQuery<UsuarioEntity> empleados = this._em.createNamedQuery("getUsuariosEmpleados", UsuarioEntity.class);
            List<UsuarioEntity> resultado = empleados.getResultList();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }
}
