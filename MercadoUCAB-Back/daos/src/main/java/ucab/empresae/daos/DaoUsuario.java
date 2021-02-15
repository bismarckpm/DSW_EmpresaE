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

    public List<UsuarioEntity> getAnalistas() {
        try{
            TypedQuery<UsuarioEntity> analistas = this._em.createQuery("select a from UsuarioEntity a where a.tipousuario.descripcion = 'Analista'", UsuarioEntity.class);
            List<UsuarioEntity> resultado = analistas.getResultList();
            return resultado;
        }catch (Exception ex) {
            return null;
        }
    }

    public UsuarioEntity getUsuarioByUsername(String username){

        try{
            TypedQuery<UsuarioEntity> usuario = this._em.createQuery("select u from UsuarioEntity u where u.username = :username", UsuarioEntity.class);
            usuario.setParameter("username", username).getSingleResult();

            UsuarioEntity resultado = usuario.getSingleResult();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }

    public List<UsuarioEntity> getUsuariosEmpleados(){

        try{
            TypedQuery<UsuarioEntity> empleados = this._em.createQuery("select a from UsuarioEntity a where a.tipousuario.descripcion = 'Analista' or a.tipousuario.descripcion = 'Administrador'", UsuarioEntity.class);
            List<UsuarioEntity> resultado = empleados.getResultList();
            return resultado;
        }catch (Exception e){
            return null;
        }
    }
}
